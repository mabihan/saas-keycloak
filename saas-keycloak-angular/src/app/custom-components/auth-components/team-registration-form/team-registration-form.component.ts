import { Component, HostListener, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from "@angular/forms";
import { Observable, Observer } from "rxjs";
import { NzValidateStatusEnum } from "@/app/core/model/enum/NzValidateStatusEnum";
import { KeycloakUserService } from "@/app/core/service/keycloak/user/keycloak-user.service";
import { UserService } from "@/app/core/service/my-little-saas-application/user/user.service";

// @ts-ignore
import timezones from 'google-timezones-json';
import { ObjectValidationResponse } from "@/app/core/model/api/api";

@Component({
  selector: 'app-team-registration-form',
  templateUrl: './team-registration-form.component.html',
  styleUrls: ['./team-registration-form.component.scss']
})
export class TeamRegistrationFormComponent implements OnInit {

  validateForm: FormGroup

  currentStep = 0;
  teamNameValidationStatus: NzValidateStatusEnum
  timeZoneList: string[];
  passwordStrength: number

  constructor(
    private fb: FormBuilder,
    private keycloakUserService: KeycloakUserService,
    private userService: UserService
  ) {

    this.passwordStrength = 0
    this.teamNameValidationStatus = NzValidateStatusEnum.VALIDATING

    this.validateForm = this.fb.group({
      userName: ['', [Validators.required], [this.userNameAsyncValidator]],
      email: ['', [Validators.email, Validators.required], [this.emailNameAsyncValidator]],
      password: ['', [Validators.required], [this.passwordComplexityAsyncValidator]],
      teamName: ['', [Validators.required]],
      teamTimeZone: ['', [Validators.required], [this.timeZoneAsyncValidator]],
    });

    this.timeZoneList = Object.keys(timezones)
      .map(value => value.replace("_", "-"))
  }

  ngOnInit() {
  }

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
  handleDeleteKeyboardEvent(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      this.next()
    }
    if (event.key === 'Backspace') {
      this.pre()
    }
  }

  submitForm(): void {

    for (const key in this.validateForm.controls) {
      if (this.validateForm.controls.hasOwnProperty(key)) {
        this.validateForm.controls[key].markAsDirty();
        this.validateForm.controls[key].updateValueAndValidity();
      }
    }

    this.keycloakUserService.registerUser()
      .then((value => {
        console.log(value)
      }))
  }

  passwordComplexityAsyncValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      setTimeout(() => {

        if (this.passwordStrength > 2) {
          observer.next({});
        } else {
          observer.next({error: true});
        }

        observer.complete();

      }, 1000);
    });

  userNameAsyncValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      setTimeout(() => {

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
          )
      }, 1000);
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
      }, 1000);
    });

  debug():void {
    console.log("[" + this.validateForm.get('userName')?.status + "]" + this.validateForm.get('userName')?.value)
    console.log("[" + this.validateForm.get('email')?.status + "]" + this.validateForm.get('email')?.value)
    console.log("[" + this.validateForm.get('password')?.status + "]" + this.validateForm.get('password')?.value)
    console.log("[" + this.validateForm.get('teamName')?.status + "]" + this.validateForm.get('teamName')?.value)
    console.log("[" + this.validateForm.get('teamTimeZone')?.status + "]" + this.validateForm.get('teamTimeZone')?.value)
  }

  confirmValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { error: true, required: true };
    } else if (control.value !== this.validateForm.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  timeZoneAsyncValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      setTimeout(() => {
        if (!this.timeZoneList.includes(control.value)) {
          // you have to return `{error: true}` to mark it as an error event
          observer.next({ error: true, invalid: true, message: "Please input a valid timezone!" });
        } else {
          observer.next({});
        }
        observer.complete();
      }, 1000);
    });


  onTeamValidationEvent($event: NzValidateStatusEnum) {
    this.teamNameValidationStatus = $event
  }

  onPasswordStrenghtEvent($event: any) {
    this.passwordStrength = $event
  }

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
