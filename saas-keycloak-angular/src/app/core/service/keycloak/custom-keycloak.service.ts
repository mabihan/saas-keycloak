import { Injectable } from '@angular/core';
import { TenantResponse } from "@/app/core/model/api/api";
import { environment } from "@/environments/environment";

declare var Keycloak: any;

@Injectable()
export class CustomKeycloakService {

  constructor() { }

  private keycloakAuth: any;

  init(tenantResponse: TenantResponse) {

    const config = {
      'url': `${environment.keycloak.url}`,
      'realm': `${environment.keycloak.defaultRealm}`,
      'clientId': 'keycloak-angular',
      'ssl-required': 'external',
      'public-client': true
    };

    this.keycloakAuth = new Keycloak(config);

    this.keycloakAuth.init({ onLoad: 'login-required' })
      .success(() => {
        console.log('Init KC avec success')
      })
      .error(() => {
        console.log('Init KC avec success')
      });
  }

  getToken(): string {
    return this.keycloakAuth.token;
  }
}
