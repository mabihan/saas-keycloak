import { ChangeDetectorRef, Component, Host, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { TeamRegistrationFormComponent } from "../team-registration-form/team-registration-form.component";

enum NzValidateStatusEnum {
  SUCCESS = 'success',
  WARNING = 'warning',
  ERROR= 'error',
  VALIDATING = 'validating'
}

@Component({
  selector: 'app-team-registration-step001',
  templateUrl: './team-registration-step001.component.html',
  styleUrls: ['./team-registration-step001.component.scss']
})
export class TeamRegistrationStep001Component implements OnInit {

  teamNameValidationStatus: NzValidateStatusEnum

  @Input() validateForm: FormGroup
  @Input() timeZoneList: string[]

  constructor(private fb: FormBuilder,
              private activatedRoute: ActivatedRoute) {
    this.teamNameValidationStatus = NzValidateStatusEnum.VALIDATING
    this.validateForm = fb.group({})
    this.timeZoneList = []
  }

  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe(params => {
      this.validateForm.patchValue({teamName: params['team']})
      setTimeout(() => {
        this.teamNameValidationStatus = NzValidateStatusEnum.SUCCESS
      }, 1000);
    });

  }
}
