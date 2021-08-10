import { Injectable } from '@angular/core';
import { ApiService } from "../api.service";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { ObjectValidationResponse, TenantRequest, UserRequest } from "@/app/core/model/api/api";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  PASSWORD_MIN_LENGTH = 8

  constructor(
    private apiService: ApiService
  ) {}

  getUsernameValidity(tenant: string, username: string): Observable<ObjectValidationResponse> {
    return this.apiService.get(`/tenant/${tenant}/user/username-validity?username=${username}`)
      .pipe(map((data: ObjectValidationResponse) => {
        return data
      }))
  }

  getEmailValidity(tenant: string, email: string): Observable<ObjectValidationResponse> {
    return this.apiService.get(`/tenant/${tenant}/user/email-validity?email=${email}`)
      .pipe(map((data: ObjectValidationResponse) => {
        return data
      }))
  }

  save(tenant: string, newUser: UserRequest): Observable<string> {
    return this.apiService.post(`/tenant/${tenant}/user/`, newUser)
      .pipe(map((data: string) => {
        console.log(data)
        return data
      }))
  }
}
