import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from "@angular/forms";
import { Observable, Observer } from "rxjs";
import { Router } from "@angular/router";

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit {

  validateForm: FormGroup
  randomImageIndex = 1;

  constructor(private fb: FormBuilder,
              private router: Router) {
    this.validateForm = fb.group({
      teamName: ['', [Validators.required]],
    })
    this.randomImageIndex = Math.floor(Math.random() * 4) + 1;
  }

  ngOnInit() {

  }

  navigateToRegistrationForm() {
    if (this.validateForm.valid) {
      this.router.navigate(["/auth/register"],
        {queryParams: {team: this.validateForm.get('teamName')?.value}})
    }
  }
}
