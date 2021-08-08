import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";

@Component({
  selector: 'app-team-registration-step002',
  templateUrl: './team-registration-step002.component.html',
  styleUrls: ['./team-registration-step002.component.scss']
})
export class TeamRegistrationStep002Component implements OnInit {

  @Input() validateForm: FormGroup

  constructor(private fb: FormBuilder) {
    this.validateForm = fb.group({})
  }

  ngOnInit(): void {
  }

}
