import { Component, OnInit } from '@angular/core';
import { KeycloakProfile } from "keycloak-js";
import { KeycloakService } from "keycloak-angular";
import { Router } from "@angular/router";

@Component({
  selector: 'app-external',
  templateUrl: './external.component.html',
  styleUrls: ['./external.component.scss']
})
export class ExternalComponent implements OnInit {

  alignRightStyle = {
    position: 'relative',
    display: 'flex',
    justifyContent: 'flex-end',
  }

  public isLoggedIn = false;
  public userProfile: any;

  public username: string = ""


  constructor(private readonly keycloak: KeycloakService) {
  }

  public async ngOnInit() {

    const response = await this.keycloak.isLoggedIn();

    new Promise<boolean>((resolve) => {setTimeout(() => resolve(response), 0)})
      .then((value) => {
        this.isLoggedIn = value;
        if (this.isLoggedIn) {
          this.userProfile = this.keycloak.loadUserProfile();
          this.userProfile.username? this.username = this.userProfile.username:"";
        }
      });
  }
}
