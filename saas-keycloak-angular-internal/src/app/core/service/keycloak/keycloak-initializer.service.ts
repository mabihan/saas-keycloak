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

    return new Promise<boolean>((resolve, reject) => {

      if(!this.keycloakClientId || !this.keycloakRealm) {
        window.location.href = `http://localhost:4200`
      }

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
