import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth-service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  if (authService.getToken()) {
    const clonedReq = req.clone({
      setHeaders:
        { Authorization: `Bearer ${authService.getToken()}` }
    });
    return next(clonedReq);
  }
  return next(req);
};
