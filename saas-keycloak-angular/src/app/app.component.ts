import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router, Routes } from "@angular/router";
import { LoginComponent } from "./pages/auth/login/login.component";
import { RegisterComponent } from "./pages/auth/register/register.component";

const noMenuRoutes = [
  '/auth',
  '/welcome'
];

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  internalContent: boolean = true;

  constructor(private router: Router) {}

  ngOnInit() {
    // listening to routing navigation event
    this.router.events.subscribe(event => this.modifyHeader(event));
  }

  modifyHeader(location: any) {
    if (location instanceof NavigationEnd) {

      for(let route of noMenuRoutes) {
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
