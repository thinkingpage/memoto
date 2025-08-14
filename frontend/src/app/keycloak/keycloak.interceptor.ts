import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import Keycloak from 'keycloak-js';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private keycloak: Keycloak) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.keycloak.token;
    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }
    return next.handle(req);
  }
}
