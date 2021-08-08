import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-internal',
  templateUrl: './internal.component.html',
  styleUrls: ['./internal.component.scss']
})
export class InternalComponent implements OnInit {

  isCollapsed: boolean;

  constructor() {
    this.isCollapsed = true
  }

  ngOnInit(): void {
    this.isCollapsed = true
  }

}
