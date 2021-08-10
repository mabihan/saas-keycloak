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

  getNamespaceValidity(namespace: string): Observable<ObjectValidationResponse> {
    return this.apiService.get(`/tenant/validate?namespace=${namespace}`)
      .pipe(map((data: ObjectValidationResponse) => {
        return data
      }))
  }

  save(newTenant: TenantRequest): Observable<string> {
    return this.apiService.post(`/tenant/`, newTenant)
      .pipe(map((data: string) => {
        console.log(data)
        return data
      }))
  }
}
