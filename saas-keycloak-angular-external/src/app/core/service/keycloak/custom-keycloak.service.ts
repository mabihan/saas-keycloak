import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { environment } from "@/environments/environment";

export interface Realm {
  displayName: string | null,
  clientId: string,
  name: string
}

export enum ActionType {
  login       = "login",
  register    = "register",
  return    = "return",
}

@Injectable({
  providedIn: 'root'
})
export class CustomKeycloakService {

  // TODO : Implement a better system for catching promise change : Try to get a valid result each X Milliseconds until Y seconds (timeout).
  DELAY_MS = 500

  private _keycloakInstance: KeycloakService
  private _currentRealm: Realm

  constructor() {
    this._keycloakInstance = new KeycloakService()
    this._currentRealm = {
      name: "",
      displayName: "",
      clientId: ""
    }
  }

  get keycloakInstance(): KeycloakService {
    return this._keycloakInstance;
  }

  get currentRealm(): Realm {
    return this._currentRealm;
  }

  set currentRealm(value: Realm) {
    this._currentRealm = value;
  }

  public initializeKeycloak(realm: Realm): Promise<boolean> {

    console.log('initialize keycloak')
    console.log(realm)

    return this._keycloakInstance.init({
      config: {
        url: environment.keycloak.url,
        realm: realm.name,
        clientId: realm.clientId,
      },
      initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html',
      },
    });
  }

  public isLoggedInWithDelay(): Promise<boolean> {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        this._keycloakInstance.isLoggedIn().then(resolve, reject);
      }, this.DELAY_MS)
    });
  }
}
