import { BrowserModule } from '@angular/platform-browser';
import { APP_INITIALIZER, NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { AuthModule } from './pages/auth/auth.module';
import { LayoutModule } from './layout/layout.module';
import { ConfigInitService } from "./core/init/config-init.service";
import { initializeKeycloak } from "./core/init/keycloak-init.factory";
import { KeycloakAngularModule, KeycloakService } from "keycloak-angular";
import { WebappModule } from './pages/webapp/webapp.module';
import { CustomComponentsModule } from './custom-components/custom-components.module';
import { CoreModule } from './core/core.module';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NzLayoutModule,
    NzMenuModule,
    AuthModule,
    LayoutModule,
    KeycloakAngularModule,
    WebappModule,
    CustomComponentsModule,
    CoreModule,
  ],
  providers: [
    ConfigInitService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService, ConfigInitService],
    },
    { provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})
export class AppModule { }
