import { Component, OnInit } from '@angular/core';
import { KeycloakProfile } from "keycloak-js";
import { KeycloakService } from "keycloak-angular";
import { KeycloakInitializerService } from "@/app/core/service/keycloak/keycloak-initializer.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public isLoggedIn = false;
  public userProfile: KeycloakProfile | null = null;

  constructor(
    private readonly keycloak: KeycloakService,
    private keycloakInitializerService: KeycloakInitializerService) { }

  public async ngOnInit() {

    this.isLoggedIn = await this.keycloak.isLoggedIn();

    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
      console.log(`userProfile from keycloak service :`);
      console.log(this.userProfile)
    }
  }

  public login() {
    this.keycloak.login(
      {
        redirectUri: `http://localhost:4300`
          + `?action=${this.keycloakInitializerService.action}`
          + `&realm=${this.keycloakInitializerService.keycloakRealm}`
          + `&clientid=${this.keycloakInitializerService.keycloakClientId}`,
        action: this.keycloakInitializerService.action
      }
    );
  }

  public logout() {
    this.keycloak.logout('http://localhost:4200/');
  }

}
