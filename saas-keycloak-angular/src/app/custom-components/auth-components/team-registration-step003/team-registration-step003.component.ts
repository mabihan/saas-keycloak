import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from "@angular/forms";
import { Observable, Observer } from "rxjs";

@Component({
  selector: 'app-team-registration-step003',
  templateUrl: './team-registration-step003.component.html',
  styleUrls: ['./team-registration-step003.component.scss']
})
export class TeamRegistrationStep003Component implements OnInit {

  @Input() validateForm: FormGroup

  optionsList: string[];

  constructor(private fb: FormBuilder) {
    this.validateForm = fb.group({})

    this.optionsList = [
      "📌 Working on a project",
      "👋 Staying connected",
      "🌩 Sharing ideas",
      "📯 Making announcements",
      "📆 Replace a meeting",
    ]
  }

  ngOnInit(): void {
  }
}
