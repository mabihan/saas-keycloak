import { Component, HostListener } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from "@angular/forms";
import { Observable, Observer } from "rxjs";
import { NzValidateStatusEnum } from "@/app/core/model/enum/NzValidateStatusEnum";
import { KeycloakUserService } from "@/app/core/service/keycloak/user/keycloak-user.service";
import { UserService } from "@/app/core/service/my-little-saas-application/user/user.service";
import { Timezone, TimezonesService } from "@/app/core/service/my-little-saas-application/timezones.service";
import { ObjectValidationResponse, TenantResponse, UserResponse } from "@/app/core/model/api/api";

@Component({
  selector: 'app-team-registration-form',
  templateUrl: './team-registration-form.component.html',
  styleUrls: ['./team-registration-form.component.scss']
})
export class TeamRegistrationFormComponent {

  FAKE_TIMEOUT_MS = 700
  validateForm: FormGroup

  currentStep = 0;
  teamNameValidationStatus: NzValidateStatusEnum
  timeZoneList: Timezone[];
  passwordStrength: number

  createdUser: UserResponse
  loading = false

  constructor(
    private fb: FormBuilder,
    private keycloakUserService: KeycloakUserService,
    private userService: UserService,
    private timezonesService: TimezonesService
  ) {
    this.createdUser = {
      uuid: "",
      tenantUuid: "",
      firstName: "John Doe",
      lastName: "Fitzgerald",
      email: "john.doe@example.com",
      username: "john-doe",
      createdTimestamp: new Date(),
      enabled: false,
      totp: false,
      emailVerified: false
    }
    this.passwordStrength = 0
    this.teamNameValidationStatus = NzValidateStatusEnum.VALIDATING

    this.validateForm = this.fb.group({
      userName: ['', [Validators.required, Validators.maxLength(30)], [this.userNameAsyncValidator]],
      email: ['', [Validators.email, Validators.required], []],
      password: ['', [Validators.required], [this.passwordComplexityAsyncValidator]],
      teamName: ['', [Validators.required, Validators.maxLength(30)]],
      teamTimeZone: ['', [Validators.required], []],
    });

    this.timeZoneList = this.timezonesService.timeZoneList
  }

  // Navigation functions
  pre(): void {
    this.currentStep -= 1;
  }

  next(): void {
    this.currentStep += 1;
  }

  done(): void {
    this.currentStep += 1;
    this.submitForm()
  }

  @HostListener('document:keyup', ['$event'])
  nextFromKeyboard(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      this.next()
    }
  }

  submitForm(): void {

    this.loading = true

    this.userService.createTenantAndUser(
      this.validateForm.get('teamName')?.value,
      this.timezonesService.getTimeZoneById(this.validateForm.get('teamTimeZone')?.value).offset,
      this.validateForm.get('userName')?.value,
      this.validateForm.get('email')?.value,
      this.validateForm.get('password')?.value,
    )
      .subscribe( (createdUser: UserResponse) => {
        this.createdUser = createdUser
        this.loading = false

        for (const key in this.validateForm.controls) {
          if (this.validateForm.controls.hasOwnProperty(key)) {
            this.validateForm.controls[key].markAsDirty();
            this.validateForm.controls[key].updateValueAndValidity();
          }
        }
      })
  }

  // Validators functions
  passwordComplexityAsyncValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      setTimeout(() => {

        if (this.passwordStrength > 2) {
          observer.next({});
        } else {
          observer.next({error: true, message: "Please choose a more secured password."});
        }

        observer.complete();

      }, this.FAKE_TIMEOUT_MS);
    });

  userNameAsyncValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      setTimeout(() => {

        const isValid = control.value.replace(/\s/g, '') === control.value;

        if (isValid) {
          observer.next({})
        } else {
          observer.next({ 'whitespace': true })
        }

        observer.complete();
        /*
        this.userService.getUsernameValidity(this.validateForm.get('teamName')?.value, control.value)
          .subscribe(
            (validity: ObjectValidationResponse) => {
              if(validity.valid) {
                observer.next({});
              } else {
                observer.next({
                  error: true,
                  message: validity.error
                });
              }
              observer.complete();
            }
          )*/
      }, this.FAKE_TIMEOUT_MS);
    });

  emailNameAsyncValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      setTimeout(() => {

        this.userService.getEmailValidity(this.validateForm.get('teamName')?.value, control.value)
          .subscribe(
            (validity: ObjectValidationResponse) => {
              if(validity.valid) {
                observer.next({});
              } else {
                observer.next({
                  error: true,
                  message: validity.error
                });
              }
              observer.complete();
            }
          )
      }, this.FAKE_TIMEOUT_MS);
    });

  timeZoneAsyncValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      setTimeout(() => {
        if (this.timezonesService.isTimeZoneNameValid(control.value)) {
          observer.next({ error: true, invalid: true, message: "Please input a valid timezone!" });
        } else {
          observer.next({});
        }
        observer.complete();
      }, this.FAKE_TIMEOUT_MS);
    });

  // Events handling
  onTeamValidationEvent($event: NzValidateStatusEnum) {
    this.teamNameValidationStatus = $event
  }

  onPasswordStrengthEvent($event: any) {
    this.passwordStrength = $event
  }

  // Getters
  get getNzValidateStatusEnum(): typeof NzValidateStatusEnum {
    return NzValidateStatusEnum;
  }

  get currentPageValidStatus(): boolean {
    switch (this.currentStep) {
      case 0: {
        return this.validateForm.get('teamName')?.status == "VALID" &&
          this.validateForm.get('teamTimeZone')?.status == "VALID";
      }
      case 1: {
        return this.validateForm.get('userName')?.status == "VALID"&&
          this.validateForm.get('password')?.status == "VALID" &&
          this.validateForm.get('email')?.status == "VALID";
      }
      default: {
        return false
      }
    }
  }
}
