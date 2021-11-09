import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from "@angular/router";
import { ActionType, CustomKeycloakService } from "@/app/core/service/keycloak/custom-keycloak.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  isLoading: boolean = false;

  isInternalContent: boolean = false;
  isExternalContent: boolean = false;

  title: string = "saas-keycloak-angular";

  constructor(private customKeycloakService: CustomKeycloakService, private router: Router) {

    console.log('Bootstrapping application')

    let action = this.getParameterFromUrl("action")
    let realm = this.getParameterFromUrl("realm")
    let clientId = this.getParameterFromUrl("clientid")

    console.log('action :' + action)
    console.log('realm :' + realm)
    console.log('clientId :' + clientId)

    /*if (action && clientId && realm) {

      if (action === ActionType.login || action === ActionType.register) {
        this.customKeycloakService.initializeKeycloak({ name: realm, clientId: clientId, displayName: null })
          .then( () => {
            this.customKeycloakService.keycloakInstance.login(
              {
                action: action,
                redirectUri: `http://localhost:4200/gateway?action=return&clientid=${clientId}&realm=${realm}`
              }
            ).then()
          })
      }
      else if (action === ActionType.return) {
        this.customKeycloakService.initializeKeycloak({ name: realm, clientId: clientId, displayName: null })
          .then( () => {
            this.customKeycloakService.currentRealm = {
              name: realm,
              clientId: clientId, displayName: null
            }
          })
        this.router.navigate(["app", "home"], {})
      }
    }*/

  }

  ngOnInit() {
    // listening to routing navigation event
    // this.router.events.subscribe(event => this.setShellMenu(event));
  }

  setShellMenu(location: any) {

    if (location instanceof NavigationEnd) {
      const url = location.url
      if (url.startsWith('/gateway')) {
        this.isExternalContent = false;
        this.isInternalContent = false;
        this.isLoading = true;
      } else if (url.startsWith('/app')) {
        this.isExternalContent = false;
        this.isInternalContent = true;
        this.isLoading = false;
      } else {
        this.isExternalContent = true;
        this.isInternalContent = false;
        this.isLoading = false;
      }
    }
  }

  getParameterFromUrl(parameter: string) {
    let re = new RegExp(parameter + "=([a-z0-9\-]+)\&?")
    let results = re.exec(window.location.href)

    if (results) {
      return results[1]
    } else {
      return ""
    }
  }
}
