import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { Observable, catchError, tap } from 'rxjs';
import { UserRes } from 'src/app/models/users/UserRes';

@Injectable({
  providedIn: 'root'
})
export class SelfService {

  private readonly url: string = 'self';

  constructor(
    private http: HttpClient,
    private toast: NgToastService
  ) { }

  getUserProfile(): Observable<UserRes> {
    return this.http.get<UserRes>(this.url);
  }

  updateUser(userUpdateReq: any): Observable<UserRes> {
    return this.http.put<UserRes>(this.url, userUpdateReq).pipe(
      tap(res => {
        console.log(res);
        this.toast.success({ detail: 'Success', summary: 'User information updated' })
      }),
      catchError(error => {
        this.toast.error({ detail: 'Error', summary: 'Unable to update information, please try again!' });
        throw error;
      })
    );
  }

  changePassword(changePassReq: any): Observable<any> {
    return this.http.patch(`${this.url}/password`, changePassReq).pipe(
      tap(res => {
        this.toast.success({ detail: 'Success', summary: 'Password changed' })
      }),
      catchError(error => {
        this.toast.error({ detail: 'Error', summary: 'Unable to change password, please try again!' });
        throw error;
      })
    );
  }

}
