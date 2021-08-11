import { Component, HostListener, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from "@angular/forms";
import { Observable, Observer } from "rxjs";
import { Router } from "@angular/router";
import { TenantResponse } from "@/app/core/model/api/api";
import { UserService } from "@/app/core/service/my-little-saas-application/user/user.service";
import { TenantService } from "@/app/core/service/my-little-saas-application/tenant/tenant.service";

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

  ngOnInit() {}

  @HostListener('document:keyup', ['$event'])
  handleDeleteKeyboardEvent(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      this.navigateToRegistrationForm()
    }
  }

  navigateToRegistrationForm() {
    if (this.validateForm.valid) {
      this.router.navigate(["/auth/register"],
        {queryParams: {team: this.validateForm.get('teamName')?.value}})
    }
  }
}
