import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { APP_BASE_HREF, registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { LayoutModule } from './layout/layout.module';
import { KeycloakAngularModule, KeycloakService } from "keycloak-angular";
import { WebappModule } from './pages/webapp/webapp.module';
import { CustomComponentsModule } from './custom-components/custom-components.module';
import { CoreModule } from './core/core.module';
import { ErrorModule } from './pages/error/error.module';
import { NzSpinModule } from "ng-zorro-antd/spin";
import { APP_INITIALIZER, NgModule } from "@angular/core";
import { KeycloakInitializerService } from "@/app/core/service/keycloak/keycloak-initializer.service";

registerLocaleData(en);

/**
 * Call initializer in keycloakInitializerService
 * @param keycloakInitializerService
 */
export function initializeKeycloakConfiguration(keycloakInitializerService: KeycloakInitializerService) {
  return (): Promise<any> => {
    return keycloakInitializerService.initializer();
  }
}

/**
 * Read parameter in current URLs.
 * @param parameter
 * @return parameter value if exist. Empty string otherwise.
 */
export function getParameterFromUrl(parameter: string) {
  let re = new RegExp(parameter + "=([a-z0-9\-]+)\&?")
  let results = re.exec(window.location.href)

  if (results) {
    return results[1]
  } else {
    return ""
  }
}

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
    LayoutModule,
    KeycloakAngularModule,
    WebappModule,
    CustomComponentsModule,
    CoreModule,
    ErrorModule,
    NzSpinModule,
  ],
  providers: [
    {
      provide: NZ_I18N,
      useValue: en_US
    },
    KeycloakInitializerService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloakConfiguration,
      deps: [KeycloakInitializerService],
      multi: true
    },
    {
      provide: 'AUTH_ACTION',
      useValue: getParameterFromUrl("action"),
    },
    {
      provide: 'AUTH_KEYCLOAK_REALM',
      useValue: getParameterFromUrl("realm"),
    },
    {
      provide: 'AUTH_KEYCLOAK_CLIENT_ID',
      useValue: getParameterFromUrl("clientid"),
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
