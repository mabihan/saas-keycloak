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

  constructor() { }

  ngOnInit(): void {
  }

}
