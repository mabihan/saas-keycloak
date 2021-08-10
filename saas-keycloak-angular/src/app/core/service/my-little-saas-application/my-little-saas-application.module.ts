import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from "./api.service";
import { TenantService } from "./tenant/tenant.service";



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [
    ApiService,
    TenantService
  ]

})
export class MyLittleSaasApplicationModule { }
