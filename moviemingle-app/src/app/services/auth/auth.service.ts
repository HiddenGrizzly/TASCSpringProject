import { AuthState } from './../../models/auth/AuthState';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NgToastService } from 'ng-angular-popup';
import { BehaviorSubject, Observable, catchError, tap, throwError } from 'rxjs';
import { LoginReq } from 'src/app/models/auth/LoginReq';
import { RegisterReq } from 'src/app/models/auth/RegisterReq';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly url: string = 'auth';

  private authState: AuthState | null;

  private authStateSubject$: BehaviorSubject<AuthState | null>;

  constructor(
    private httpClient: HttpClient,
    private jwtHelperService: JwtHelperService,
    private router: Router,
    private toast: NgToastService
  ) {
    this.authState = this.getAuthState();
    this.authStateSubject$ = new BehaviorSubject(this.authState);
  }

  register(dto: RegisterReq): Observable<any> {
    return this.httpClient.post(`${this.url}/register`, dto);
  }

  login(dto: LoginReq): Observable<any> {
    return this.httpClient.post(`${this.url}/login`, dto).pipe(
      tap(res => {
        this.toast.success({ detail: 'Login successfully', summary: 'Welcome back!' });
      }),
      catchError(error => {
        this.toast.error({ detail: 'Login fail', summary: 'Please try again later!' })
        throw error;
      })
    );
  }

  logout(): void {
    this.clearAuthState();
    this.router.navigate(['/']);
    this.toast.info({ detail: 'Logout', summary: 'See you later!' })
  }

  setAuthState(state: AuthState): void {
    localStorage.setItem('authState', JSON.stringify(state));
    this.authStateSubject$.next(state);
  }

  getAuthState(): AuthState | null {
    const state = localStorage.getItem('authState');
    return state ? JSON.parse(state) : null;
  }

  observeAuthState(): Observable<AuthState | null> {
    return this.authStateSubject$.asObservable();
  }

  clearAuthState(): void {
    localStorage.removeItem('authState');
    this.authStateSubject$.next(null);
  }

  decodeToken(): any {
    return this.jwtHelperService.decodeToken();
  }

  refreshAccessToken(refreshToken: string): Observable<AuthState> {
    return this.httpClient.post<AuthState>(`${this.url}/tokens`, { refreshToken: refreshToken });
  }

}
