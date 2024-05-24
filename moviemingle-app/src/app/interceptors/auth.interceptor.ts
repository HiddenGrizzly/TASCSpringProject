import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthService } from '../services/auth/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService, private jwtHelperService: JwtHelperService) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const authState = this.authService.getAuthState();
    if (authState) {
      const token = authState.accessToken;
      if (token && !this.jwtHelperService.isTokenExpired(token)) {
        let modifiedReq = request.clone({
          setHeaders: {
            Authorization: `Bearer ${token}`
          }
        });
        console.log(token);
        return next.handle(modifiedReq);
      }
    }
    return next.handle(request);
  }
}
