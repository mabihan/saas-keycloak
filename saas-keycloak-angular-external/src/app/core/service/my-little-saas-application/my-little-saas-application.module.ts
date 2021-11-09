import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from "./api.service";
import { TenantService } from "./tenant/tenant.service";
import { ApiErrorService } from "@/app/core/service/my-little-saas-application/error/api-error.service";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { HttpTokenInterceptor } from "@/app/core/interceptors";
import { JwtService } from "@/app/core/service/my-little-saas-application/jwt.service";
import { ErrorResolverService } from "@/app/core/service/my-little-saas-application/error/error-resolver.service";



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [
    ApiService,
    TenantService,
    ApiErrorService,
    { provide: HTTP_INTERCEPTORS, useClass: HttpTokenInterceptor, multi: true },
    JwtService,
    ErrorResolverService
  ]

})
export class MyLittleSaasApplicationModule { }
