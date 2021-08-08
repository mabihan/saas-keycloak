import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from "@angular/forms";
import { Observable, Observer } from "rxjs";

@Component({
  selector: 'app-team-registration-step001',
  templateUrl: './team-registration-step001.component.html',
  styleUrls: ['./team-registration-step001.component.scss']
})
export class TeamRegistrationStep001Component implements OnInit {

  @Input() validateForm: FormGroup
  @Input() timeZoneList: string[]

  constructor(private fb: FormBuilder) {
    this.validateForm = fb.group({})
    this.timeZoneList = []
  }

  ngOnInit(): void {
  }
}
