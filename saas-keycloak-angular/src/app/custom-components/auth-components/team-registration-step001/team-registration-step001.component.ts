import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { NzValidateStatusEnum } from "@/app/core/model/enum/NzValidateStatusEnum";
import { TenantService } from "@/app/core/service/my-little-saas-application/tenant/tenant.service";
import { ObjectValidationResponse } from "@/app/core/model/api/api";
import { Timezone } from "@/app/core/service/my-little-saas-application/timezones.service";

@Component({
  selector: 'app-team-registration-step001',
  templateUrl: './team-registration-step001.component.html',
  styleUrls: ['./team-registration-step001.component.scss']
})
export class TeamRegistrationStep001Component implements OnInit {

  @Input() validateForm: FormGroup
  @Input() timeZoneList: Timezone[]

  FAKE_TIMEOUT_MS = 700

  teamNameValidationStatus: NzValidateStatusEnum | string
  teamNameValidationError: boolean
  teamNameValidationErrorMessage: string
  teamNameValidationSuccessMessage: string

  @Output() teamNameValidationEvent = new EventEmitter();

  constructor(private fb: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private tenantService: TenantService) {

    this.teamNameValidationStatus = ""
    this.teamNameValidationError = false
    this.teamNameValidationErrorMessage = ""
    this.teamNameValidationSuccessMessage = ""

    this.validateForm = fb.group({})
    this.timeZoneList = []
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {

      let fetchTeamName: string = params['team'];

      if (fetchTeamName.length) {
        this.validateForm.patchValue({teamName: params['team']})
        this.validateTeamName(params['team'])
      }
    });
  }

  validateTeamName(newTeamName: string) {
    this.teamNameValidationStatus = NzValidateStatusEnum.VALIDATING
    setTimeout(() => {
      if (newTeamName.length > 30) {
        this.teamNameValidationStatus = NzValidateStatusEnum.ERROR
        this.teamNameValidationError = true
        this.teamNameValidationErrorMessage = "Team's name cannot exceed 30 characters."
        this.teamNameValidationSuccessMessage = ""
      } else if (newTeamName.length <= 30 && newTeamName.length > 0) {
        this.tenantService.getNewNamespaceValidity(newTeamName)
          .subscribe(
            (validity: ObjectValidationResponse) => {

              console.log(validity)

              if(validity.valid) {
                this.teamNameValidationStatus = NzValidateStatusEnum.SUCCESS
                this.teamNameValidationError = false
                this.teamNameValidationErrorMessage = ""
                this.teamNameValidationSuccessMessage = validity.message
              } else {
                this.teamNameValidationStatus = NzValidateStatusEnum.ERROR
                this.teamNameValidationError = true
                this.teamNameValidationErrorMessage = validity.error
                this.teamNameValidationSuccessMessage = ""
              }
            }
          )
      } else {
        this.teamNameValidationStatus = NzValidateStatusEnum.ERROR
        this.teamNameValidationError = true
        this.teamNameValidationErrorMessage = "You team's name is required."
        this.teamNameValidationSuccessMessage = ""
      }
      this.teamNameValidationEvent.emit(this.teamNameValidationStatus)
    }, this.FAKE_TIMEOUT_MS);
  }
}
