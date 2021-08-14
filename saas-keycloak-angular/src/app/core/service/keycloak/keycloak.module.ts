import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomKeycloakService } from "@/app/core/service/keycloak/custom-keycloak.service";



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [
    CustomKeycloakService
  ]
})
export class KeycloakModule { }
