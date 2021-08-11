import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BoardingRoutingModule } from './boarding-routing.module';
import { BoardingComponent } from "@/app/pages/boarding/boarding.component";
import { NzCardModule } from "ng-zorro-antd/card";
import { NzAvatarModule } from "ng-zorro-antd/avatar";
import { NzIconModule } from "ng-zorro-antd/icon";


@NgModule({
  declarations: [
    BoardingComponent
  ],
  imports: [
    CommonModule,
    BoardingRoutingModule,
    NzCardModule,
    NzAvatarModule,
    NzIconModule
  ]
})
export class BoardingModule { }
