// import { HttpInterceptorFn } from '@angular/common/http';
// import { inject } from '@angular/core';
// import Keycloak from 'keycloak-js';
//
// export const keycloakHttpInterceptor: HttpInterceptorFn = (req, next) => {
//   const keycloak = inject(Keycloak);
//
//   const excludedUrls = ['/public'];
//   const shouldExclude = excludedUrls.some(url => req.url.includes(url));
//
//   if (shouldExclude || !keycloak.authenticated || !keycloak.token) {
//     return next(req);
//   }
//
//   const authReq = req.clone({
//     setHeaders: {
//       Authorization: `Bearer ${keycloak.token}`
//     }
//   });
//
//   return next(authReq);
// };
