import { AuthState } from './../../models/auth/AuthState';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginReq } from 'src/app/models/auth/LoginReq';
import { RegisterReq } from 'src/app/models/auth/RegisterReq';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly url: string = 'auth';

  authState: AuthState | null;

  authStateSubject$: BehaviorSubject<AuthState | null>;

  constructor(
    private httpClient: HttpClient,
    private jwtHelperService: JwtHelperService,
    private router: Router
  ) {
    this.authState = this.getAuthState();
    this.authStateSubject$ = new BehaviorSubject(this.authState);
  }

  // register a new user account
  register(dto: RegisterReq): Observable<any> {
    return this.httpClient.post(`${this.url}/register`, dto);
  }

  // login user
  login(dto: LoginReq): Observable<any> {
    return this.httpClient.post(`${this.url}/login`, dto);
  }

  // logout
  logout(): void {
    this.clearAuthState();
    this.router.navigate(['/']);
  }

  // set auth state
  setAuthState(state: AuthState): void {
    localStorage.setItem('authState', JSON.stringify(state));
    this.authStateSubject$.next(state);
  }

  // get auth state
  getAuthState(): AuthState | null {
    const state = localStorage.getItem('authState');
    return state ? JSON.parse(state) : null;
  }

  // observe auth state
  observeAuthState(): Observable<AuthState | null> {
    return this.authStateSubject$.asObservable();
  }

  // clear state
  clearAuthState(): void {
    localStorage.removeItem('authState');
    this.authStateSubject$.next(null);
  }

  // decode token
  decodeToken(): any {
    return this.jwtHelperService.decodeToken();
  }

}
