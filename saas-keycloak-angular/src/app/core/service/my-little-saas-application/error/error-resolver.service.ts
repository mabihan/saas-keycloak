import { Injectable } from '@angular/core';
import { HttpErrorResponse } from "@angular/common/http";
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from "@angular/router";
import { ApiErrorService } from "@/app/core/service/my-little-saas-application/error/api-error.service";
import { Observable, of } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ErrorResolverService implements Resolve<HttpErrorResponse> {

  constructor(
    private apiErrorService: ApiErrorService,
  ) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any> {
    return of(this.apiErrorService.currentError)
  }
}
