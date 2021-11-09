import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";

@Component({
  selector: 'app-user-login-step001',
  templateUrl: './user-login-step001.component.html',
  styleUrls: ['./user-login-step001.component.scss']
})
export class UserLoginStep001Component implements OnInit {

  @Input() validateForm: FormGroup

  constructor(private fb: FormBuilder) {
    this.validateForm = fb.group({})
  }

  ngOnInit(): void {

  }

}
