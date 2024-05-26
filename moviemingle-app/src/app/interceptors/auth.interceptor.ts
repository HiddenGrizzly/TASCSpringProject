import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, switchMap, throwError } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private authService: AuthService
  ) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const authState = this.authService.getAuthState();
    if (!authState || !authState.accessToken || !authState.refreshToken || request.url.endsWith('/auth/tokens')) {
      const cloned = request.clone({
        setHeaders: {
          Authorization: ''
        }
      })
      return next.handle(cloned);
    }

    const accessToken = authState.accessToken;
    const refreshToken = authState.refreshToken;

    const requestWithCurrentToken = request.clone({
      setHeaders: {
        Authorization: `Bearer ${accessToken}`
      }
    });
    return next.handle(requestWithCurrentToken).pipe(
      catchError(
        err => {
          if (err instanceof HttpErrorResponse && err.status === 401) {
            return this.authService.refreshAccessToken(refreshToken).pipe(
              switchMap(res => {
                this.authService.setAuthState(res);
                const requestWithNewToken = request.clone({
                  setHeaders: {
                    Authorization: `Bearer ${res.accessToken}`
                  }
                })
                return next.handle(requestWithNewToken);
              }),
              catchError(err => {
                console.log(err);
                this.authService.logout();
                return throwError(() => new Error('Reauthentication failed.'));
              })
            );
          }
          return throwError(() => err);
        }
      )
    );
  }
}
