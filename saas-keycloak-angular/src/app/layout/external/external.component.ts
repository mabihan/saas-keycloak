import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-external',
  templateUrl: './external.component.html',
  styleUrls: ['./external.component.scss']
})
export class ExternalComponent implements OnInit {

  alignRightStyle = {
    position: 'relative',
    display: 'flex',
    justifyContent: 'flex-end',
  }

  public isLoggedIn = false;

  public username: string = ""


  constructor() {
  }

  public async ngOnInit() {

  }
}
