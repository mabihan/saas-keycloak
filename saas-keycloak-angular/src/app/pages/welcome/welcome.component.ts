import { Component, OnInit } from '@angular/core';
import { Container, Main } from "tsparticles";

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit {

  randomImageIndex = 1;

  constructor() {
    this.randomImageIndex = Math.floor(Math.random() * 4) + 1;
  }

  ngOnInit() {

  }
}
