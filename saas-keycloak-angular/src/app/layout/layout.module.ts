import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExternalComponent } from './external/external.component';
import { InternalComponent } from './internal/internal.component';
import { NzLayoutModule } from "ng-zorro-antd/layout";
import { AppRoutingModule } from "../app-routing.module";
import { NzMenuModule } from "ng-zorro-antd/menu";
import { NzIconModule } from "ng-zorro-antd/icon";
import { IconDefinition } from "@ant-design/icons-angular";
import {
  DashboardOutline, FormOutline, GithubOutline, HeartOutline, HeartTwoTone,
  MenuFoldOutline, MenuUnfoldOutline,
  SlackCircleFill,
  SlackOutline,
  TwitterOutline, TwitterSquareFill
} from "@ant-design/icons-angular/icons";
import { NzSwitchModule } from "ng-zorro-antd/switch";
import { FormsModule } from "@angular/forms";

const icons: IconDefinition[] = [
  SlackOutline,
  SlackCircleFill,
  TwitterOutline,
  DashboardOutline,
  MenuFoldOutline,
  FormOutline,
  GithubOutline,
  HeartTwoTone,
  TwitterSquareFill,
  MenuUnfoldOutline
];

@NgModule({
  declarations: [ExternalComponent, InternalComponent],
  exports: [
    InternalComponent,
    ExternalComponent
  ],
  imports: [
    CommonModule,
    NzLayoutModule,
    AppRoutingModule,
    NzMenuModule,
    NzIconModule.forRoot(icons),
    NzSwitchModule,
    FormsModule,
  ]
})
export class LayoutModule { }
