import {Component, inject} from '@angular/core';
import Keycloak from 'keycloak-js';
import {UserProfileService} from '../keycloak/user-profile.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  templateUrl: './menu.component.html',
})

export class MenuComponent {

  private readonly keycloak = inject(Keycloak);
  userProfileService = inject(UserProfileService);

  login() {
    this.keycloak.login();
  }

  logout() {
    this.keycloak.logout();
  }

  protected readonly Object = Object;
}
