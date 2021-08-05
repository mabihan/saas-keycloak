import { Injectable } from '@angular/core';
import { KeycloakService } from "keycloak-angular";
import { KeycloakProfile } from "keycloak-js";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private keycloak: KeycloakService) {}

  async registerUser() {

    await this.keycloak
      .register({ action: 'register' })
      .then( value => {
        return value
      })
  }

}
