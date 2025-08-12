import {effect, inject, Injectable} from '@angular/core';
import Keycloak from 'keycloak-js';
import {KEYCLOAK_EVENT_SIGNAL, KeycloakEventType, ReadyArgs, typeEventArgs} from 'keycloak-angular';


@Injectable({ providedIn: 'root' })
export class KeycloakService {
  private readonly keycloak = inject(Keycloak)

  authenticated: boolean = false;
  keycloakStatus: string | undefined;

  constructor() {
    const keycloakSignal = inject(KEYCLOAK_EVENT_SIGNAL);

    effect(() => {
      const keycloakEvent = keycloakSignal();
      this.keycloakStatus = keycloakEvent.type;

      console.log("------------------------------------------------------")
      console.log(this.keycloak);
      console.log(keycloakSignal());
      console.log("------------------------------------------------------")

      if (keycloakEvent.type === KeycloakEventType.Ready) {
        this.authenticated = typeEventArgs<ReadyArgs>(keycloakEvent.args);
      }

      if (keycloakEvent.type === KeycloakEventType.AuthLogout) {
        console.log("----- LOGGED OUT -----: " + keycloakEvent.type)
        this.authenticated = false;
      }

      if (!this.authenticated) {
        console.log("----- NOT AUTHENTICATED -----")
        console.log(keycloakEvent);
      }
    });
  }
}
