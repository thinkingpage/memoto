import {Component, effect, inject} from '@angular/core';
import Keycloak from 'keycloak-js';
import {KEYCLOAK_EVENT_SIGNAL, KeycloakEventType, ReadyArgs, typeEventArgs} from 'keycloak-angular';

@Component({
  selector: 'app-menu',
  templateUrl: '../menu.component.html'
})

export class MenuComponent {
  private readonly keycloak = inject(Keycloak)

  authenticated: boolean = false;
  keycloakStatus: string | undefined;
  profile: object | undefined;

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


      if (keycloakEvent.type === KeycloakEventType.Ready && this.keycloak.authenticated) {
        (async () => {
          const profile = await this.keycloak.loadUserProfile();
        })();
      }
    });
  }

  login()
  {
    this.keycloak.login();
  }

  logout()
  {
    this.keycloak.logout();
  }
}
