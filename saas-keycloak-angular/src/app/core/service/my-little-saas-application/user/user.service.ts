import { Injectable } from '@angular/core';
import { ApiService } from "../api.service";
import { Observable, Subject } from "rxjs";
import { map } from "rxjs/operators";
import {
  ObjectValidationResponse,
  TenantRequest,
  TenantResponse,
  UserRequest,
  UserResponse
} from "@/app/core/model/api/api";
import { TenantService } from "@/app/core/service/my-little-saas-application/tenant/tenant.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private apiService: ApiService,
    private tenantService: TenantService
  ) {}

  getUsernameValidity(tenantUuid: string, username: string): Observable<ObjectValidationResponse> {
    return this.apiService.get(`/api/v1/tenant/${tenantUuid}/user/username-validity?username=${username}`)
      .pipe(map((data: ObjectValidationResponse) => {
        return data
      }))
  }

  getEmailValidity(tenantUuid: string, email: string): Observable<ObjectValidationResponse> {
    return this.apiService.get(`/api/v1/tenant/${tenantUuid}/user/email-validity?email=${email}`)
      .pipe(map((data: ObjectValidationResponse) => {
        return data
      }))
  }

  save(tenantUuid: string, newUser: UserRequest): Observable<UserResponse> {
    return this.apiService.post(`/api/v1/tenant/${tenantUuid}/user/`, newUser)
      .pipe(map((data: UserResponse) => {
        return data
      }))
  }

  /**
   * Create a tenant in api, and the create an user in this tenant namespace.
   * @param tenantNamespace
   * @param tenantTimeZone
   * @param username
   * @param email
   * @param password
   *
   * @return Created tenant's details as an observable.
   */
  createTenantAndUser(
    tenantNamespace: string,
    tenantTimeZone: string,
    username: string,
    email: string,
    password: string): Observable<UserResponse> {

    let newTenant: TenantRequest = {
      namespace: tenantNamespace,
      timeZone: tenantTimeZone
    }

    let newUser: UserRequest = {
      firstName: "",
      lastName: "",
      email: email,
      username: username,
      password: password
    }


    const subject = new Subject<UserResponse>();

    this.tenantService.save(newTenant)
      .pipe(
        map( ((createTenant: TenantResponse) =>  {
          this.save(createTenant.uuid, newUser)
            .subscribe(

              (createdUser: UserResponse) => {
                subject.next(createdUser);
              }
            )
        }) )
      )
      .subscribe()

    return subject.asObservable();
  }
}
