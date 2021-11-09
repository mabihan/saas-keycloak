import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyLittleSaasApplicationModule } from './my-little-saas-application/my-little-saas-application.module';
import { KeycloakModule } from './keycloak/keycloak.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MyLittleSaasApplicationModule,
    KeycloakModule
  ]
})
export class ServiceModule { }
