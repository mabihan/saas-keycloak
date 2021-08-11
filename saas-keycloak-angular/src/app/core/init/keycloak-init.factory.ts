import { KeycloakService } from "keycloak-angular";
import { switchMap } from "rxjs/operators";
import { fromPromise } from 'rxjs/internal-compatibility';
import { ConfigInitService } from "@/app/core/init/config-init.service";

export function initializeKeycloak(
  keycloak: KeycloakService,
  configService: ConfigInitService
  ) {
    return () =>
      configService.getConfig()
        .pipe(
          switchMap<any, any>((config) => {

            return fromPromise(keycloak.init({
              config: {
                url: config['KEYCLOAK_URL'] + '/auth',
                realm: config['KEYCLOAK_REALM'],
                clientId: config['KEYCLOAK_CLIENT_ID'],
              },
              initOptions: {
              onLoad: 'check-sso',
                silentCheckSsoRedirectUri: window.location.origin + '/assets/sso/silent-check-sso.html',
            },
            }))

          })
        ).toPromise()
}

