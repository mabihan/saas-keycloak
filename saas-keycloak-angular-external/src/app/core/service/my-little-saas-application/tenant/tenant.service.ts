import { Injectable } from '@angular/core';
import { ApiService } from "../api.service";
import { Observable } from "rxjs";

import { map } from 'rxjs/operators';
import { ObjectValidationResponse, TenantRequest, TenantResponse } from "@/app/core/model/api/api";

@Injectable({
  providedIn: 'root'
})
export class TenantService {

  constructor(
    private apiService: ApiService
  ) {}

  get(namespace: string): Observable<TenantResponse> {
    return this.apiService.get(`/api/v1/tenant?namespace=${namespace}`)
      .pipe(map((data: TenantResponse) => {
        return data
      }))

  }

  getNewNamespaceValidity(namespace: string): Observable<ObjectValidationResponse> {
    return this.apiService.get(`/api/v1/tenant/new/validate?namespace=${namespace}`)
      .pipe(map((data: ObjectValidationResponse) => {
        return data
      }))
  }

  getNamespaceValidity(namespace: string): Observable<ObjectValidationResponse> {
    return this.apiService.get(`/api/v1/tenant/validate?namespace=${namespace}`)
      .pipe(map((data: ObjectValidationResponse) => {
        return data
      }))
  }

  save(newTenant: TenantRequest): Observable<TenantResponse> {
    return this.apiService.post(`/api/v1/tenant/`, newTenant)
      .pipe(map((data: TenantResponse) => {
        return data
      }))

  }
}
