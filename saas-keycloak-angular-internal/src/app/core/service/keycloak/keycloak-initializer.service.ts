import { Inject, Injectable } from '@angular/core';
import { KeycloakService } from "keycloak-angular";

@Injectable()
export class KeycloakInitializerService {

  constructor(
    private keycloak: KeycloakService,
    @Inject('AUTH_ACTION') private _action: string,
    @Inject('AUTH_KEYCLOAK_REALM') private _keycloakRealm: string,
    @Inject('AUTH_KEYCLOAK_CLIENT_ID') private _keycloakClientId: string,
  ) {
  }


  get action(): string {
    return this._action;
  }

  get keycloakRealm(): string {
    return this._keycloakRealm;
  }

  get keycloakClientId(): string {
    return this._keycloakClientId;
  }

  public initializer(): Promise<boolean> {

    /**
    return new Promise<boolean>((resolve, reject) => {
      console.log("KeycloakInitializerService.initializer() called");

      console.log(`action : ${this.action}`);
      console.log(`keycloakRealm : ${this.keycloakRealm}`);
      console.log(`keycloakClientId : ${this.keycloakClientId}`);

      setTimeout(() => {
        console.log('KeycloakInitializerService Finished');
        resolve(true);
      }, 2000);
    });
     */

    return new Promise<boolean>((resolve, reject) => {

      console.log("KeycloakInitializerService.initializer() called");

      console.log(`action : ${this._action}`);
      console.log(`keycloakRealm : ${this._keycloakRealm}`);
      console.log(`keycloakClientId : ${this._keycloakClientId}`);

      resolve(this.keycloak.init({
        config: {
          url: 'http://localhost:8080/auth',
          realm: this._keycloakRealm,
          clientId: this._keycloakClientId,
        },
        initOptions: {
          onLoad: 'check-sso',
          silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html',
        },
      }));

    })
  }
}
