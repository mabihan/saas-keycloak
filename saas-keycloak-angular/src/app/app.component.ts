import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from "@angular/router";

const externalAppRoutes = [
  '/auth',
  '/welcome',
  '/error',
  '/boarding',
  '/roadmap'
];

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  internalContent: boolean = true;
  title: string = "saas-keycloak-angular";

  constructor(private router: Router) {}

  ngOnInit() {
    // listening to routing navigation event
    this.router.events.subscribe(event => this.modifyHeader(event));
  }

  modifyHeader(location: any) {
    if (location instanceof NavigationEnd) {

      for(let route of externalAppRoutes) {
        if( location.urlAfterRedirects.startsWith(route)) {
          this.internalContent = false;
          break;
        } else {
          this.internalContent = true;
        }
      }
    }
  }
}
