import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PageReq } from 'src/app/models/pages/PageReq';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly baseUrl: string = 'users';

  constructor(private http: HttpClient) { }

  getAll(page: PageReq | null): Observable<any> {
    return this.http.get(this.baseUrl, {
      params: { ...page }
    })
  }

  getById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

}
