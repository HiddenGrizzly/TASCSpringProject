import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const authState = authService.getAuthState();

  if (authState) {
    const userRoles: string[] = authState.roles;
    const allowedRoles: string[] = route.data['roles'];
    if (allowedRoles) {
      for (let role of allowedRoles) {
        if (userRoles.includes(role)) {
          return true;
        }
      }
      router.navigateByUrl('/');
      return false;
    }
    return true;
  }
  router.navigateByUrl('/');
  return false;
};
