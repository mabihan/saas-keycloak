import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoadmapRoutingModule } from './roadmap-routing.module';
import { RoadmapComponent } from './roadmap.component';
import { NzCardModule } from "ng-zorro-antd/card";


@NgModule({
  declarations: [
    RoadmapComponent
  ],
  imports: [
    CommonModule,
    RoadmapRoutingModule,
    NzCardModule
  ]
})
export class RoadmapModule { }
