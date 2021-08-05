import { APP_INITIALIZER, NgModule } from '@angular/core';

import { WelcomeRoutingModule } from './welcome-routing.module';

import { WelcomeComponent } from './welcome.component';
import { CommonModule } from "@angular/common";
import { NzButtonModule } from "ng-zorro-antd/button";
import { HelpComponent } from './help/help.component';
import { AboutComponent } from './about/about.component';
import { FunctionalitiesComponent } from './functionalities/functionalities.component';

@NgModule({
  imports: [WelcomeRoutingModule, CommonModule, NzButtonModule],
  declarations: [WelcomeComponent, HelpComponent, AboutComponent, FunctionalitiesComponent],
  exports: [WelcomeComponent],
  providers: []
})
export class WelcomeModule { }
