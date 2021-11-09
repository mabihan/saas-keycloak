import { Injectable } from '@angular/core';
import { CanActivate } from "@angular/router";
import { KeycloakService } from "keycloak-angular";
import { KeycloakInitializerService } from "@/app/core/service/keycloak/keycloak-initializer.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(
    private readonly keycloakService: KeycloakService,
    private keycloakInitializerService: KeycloakInitializerService
  ) { }

  canActivate(): Promise<boolean> {
    console.log("AuthGuardService.canActivate called");

    return this.keycloakService.isLoggedIn()
      .then((loggedIn: boolean) => {
        console.log('loggedIn : ' + loggedIn)

        if(loggedIn) {
          console.log("User is logged in");
          console.log(this.keycloakService.loadUserProfile());
          return true;
        } else {
          console.log("User is not logged in");
          console.log("Redirecting user to keycloak");
          this.keycloakService.login(
            {
              redirectUri: `http://localhost:4300`
                + `?action=${this.keycloakInitializerService.action}`
                + `&realm=${this.keycloakInitializerService.keycloakRealm}`
                + `&clientid=${this.keycloakInitializerService.keycloakClientId}`,
              action: this.keycloakInitializerService.action
            })
          return false;
        }
      })
      .catch(() => {
        return false;
      })
      .finally(() => {
        return false;
      })
   }
}
