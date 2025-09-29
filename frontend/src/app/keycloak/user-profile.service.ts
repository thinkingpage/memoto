import { Injectable, inject, effect, signal } from '@angular/core';
import Keycloak from 'keycloak-js';
import { KEYCLOAK_EVENT_SIGNAL, KeycloakEventType} from 'keycloak-angular';

@Injectable({ providedIn: 'root' })
export class UserProfileService {
  private keycloak = inject(Keycloak);
  private keycloakSignal = inject(KEYCLOAK_EVENT_SIGNAL);
  public profile = signal<Keycloak.KeycloakProfile | null> (null);

  constructor() {
    effect(() => {
      const event = this.keycloakSignal();

      if (event.type === KeycloakEventType.Ready && this.keycloak.authenticated) {
        this.loadUserProfile();
      }

      else if (event.type === KeycloakEventType.AuthLogout) {
        this.profile.set(null);
      }
    });
  }


  async loadUserProfile() {
    if (this.keycloak.authenticated) {
      const p = await this.keycloak.loadUserProfile();
      this.profile.set(p);
    }
  }
}
