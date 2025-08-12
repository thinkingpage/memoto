import { Injectable, inject } from '@angular/core';
import Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {
  private keycloak = inject(Keycloak);

  profile: Keycloak.KeycloakProfile | null = null;

  async loadUserProfile(): Promise<void> {

    if (this.keycloak.authenticated) {
      this.profile = await this.keycloak.loadUserProfile();
    } else {
      this.profile = null;
    }
  }
}
