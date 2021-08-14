import { Component, OnInit } from '@angular/core';
import { KeycloakProfile } from "keycloak-js";
import { Router } from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public isLoggedIn = false;
  public userProfile: KeycloakProfile | null = null;

  constructor(private router: Router) { }

  public async ngOnInit() {

  }

  public login() {
  }

  public logout() {
  }

}
