import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WebappRoutingModule } from './webapp-routing.module';
import { HomeComponent } from './home/home.component';


@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    WebappRoutingModule
  ]
})
export class WebappModule { }
