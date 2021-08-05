import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from "@angular/forms";
import { Observable, Observer } from "rxjs";

@Component({
  selector: 'app-team-registration-step002',
  templateUrl: './team-registration-step002.component.html',
  styleUrls: ['./team-registration-step002.component.scss']
})
export class TeamRegistrationStep002Component implements OnInit {

  @Input() validateForm: FormGroup
  @Input() timeZoneList: string[]

  constructor(private fb: FormBuilder) {
    this.validateForm = fb.group({})
    this.timeZoneList = []
  }

  ngOnInit(): void {
  }

}
