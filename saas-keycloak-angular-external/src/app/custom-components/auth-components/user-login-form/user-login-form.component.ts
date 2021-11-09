import { Component, HostListener } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from "@angular/forms";
import { NzValidateStatusEnum } from "@/app/core/model/enum/NzValidateStatusEnum";
import { Observable, Observer } from "rxjs";
import { ObjectValidationResponse, TenantResponse } from "@/app/core/model/api/api";
import { TenantService } from "@/app/core/service/my-little-saas-application/tenant/tenant.service";
import { KeycloakService } from "keycloak-angular";
import { ActionType, CustomKeycloakService } from "@/app/core/service/keycloak/custom-keycloak.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-user-login-form',
  templateUrl: './user-login-form.component.html',
  styleUrls: ['./user-login-form.component.scss']
})
export class UserLoginFormComponent {

  FAKE_TIMEOUT_MS = 400
  validateForm: FormGroup

  currentStep = 0;

  loading = false

  constructor(
    private fb: FormBuilder,
    private customKeycloakService: CustomKeycloakService,
    private tenantService: TenantService,
    private router: Router
  ) {

    this.validateForm = this.fb.group({
      teamName: ['', [Validators.required], [this.teamNameAsyncValidator]],
    });
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

  debug(): void {
    console.log(this.validateForm.get('teamName'))
  }

  submitForm(): void {
    this.loading = true

    this.tenantService.get(this.validateForm.get('teamName')?.value)
      .subscribe( (tenantResponse: TenantResponse) => {

        const angularClient = tenantResponse.clients.filter( value => value.name.includes('angular') )[0]

        if (angularClient) {
          window.location.href = `http://localhost:4300`
            + `?action=${ActionType.login}`
            + `&realm=${tenantResponse.keycloakRealm}`
            + `&clientid=${angularClient.clientId}`;
        } else {
          throw Error('Keycloak client matching angular pattern not found')
        }
      })
  }

  // Validators
  teamNameAsyncValidator = (control: FormControl) =>
    new Observable((observer: Observer<ValidationErrors | null>) => {
      setTimeout(() => {

        this.tenantService.getNamespaceValidity(this.validateForm.get('teamName')?.value)
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

  // Getters
  get getNzValidateStatusEnum(): typeof NzValidateStatusEnum {
    return NzValidateStatusEnum;
  }

  get currentPageValidStatus(): boolean {
    switch (this.currentStep) {
      case 0: {
        return this.validateForm.get('teamName')?.status == "VALID"
      }
      default: {
        return false
      }
    }
  }
}
