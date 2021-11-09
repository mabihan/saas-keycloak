import { Component, OnInit } from '@angular/core';

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

  constructor() {

  }

  ngOnInit() {
  }

}
