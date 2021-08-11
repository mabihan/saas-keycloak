import { Injectable } from '@angular/core';
import { KeycloakService } from "keycloak-angular";
import { KeycloakProfile } from "keycloak-js";
import { ConfigInitService } from "@/app/core/init/config-init.service";
import { switchMap } from "rxjs/operators";
import { TenantResponse } from "@/app/core/model/api/api";

@Injectable({
  providedIn: 'root'
})
export class KeycloakUserService {

  constructor(private configService: ConfigInitService,
              private keycloak: KeycloakService) {}

  async init(tenantResponse: TenantResponse) {

    await this.configService.getConfig()
      .pipe(
        switchMap<any, any>((config) => {
          return  this.keycloak.init({
            config: {
              url: config['KEYCLOAK_URL'] + '/auth',
              realm: tenantResponse.keycloakRealm,
              clientId: config['KEYCLOAK_CLIENT_ID'],
            },
            initOptions: {
              onLoad: 'check-sso',
              silentCheckSsoRedirectUri: window.location.origin + '/assets/sso/silent-check-sso.html',
            },
          });
        }))
  }
}
