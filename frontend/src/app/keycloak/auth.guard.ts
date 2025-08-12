import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import Keycloak from 'keycloak-js';

export const authGuard: CanActivateFn = (route, state) => {
  // const keycloak = inject(Keycloak);

  // if (keycloak.isTokenExpired()) {
  //   return true;
  // }

  return false;
};
