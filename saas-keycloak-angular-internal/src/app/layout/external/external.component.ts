import { Component, Input, OnInit } from '@angular/core';

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

  public username: string = ""
  isLoggedIn: boolean;


  constructor() {
    this.isLoggedIn = false
  }

  public async ngOnInit() {

  }
}
