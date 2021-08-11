import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServiceModule } from './service/service.module';
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { HttpTokenInterceptor } from "@/app/core/interceptors";
import { JwtService } from "@/app/core/service/my-little-saas-application/jwt.service";



@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    ServiceModule
  ],
})
export class CoreModule { }
