import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Observable ,  throwError } from 'rxjs';

import { catchError } from 'rxjs/operators';
import { environment } from "@/environments/environment";
import { ApiErrorService } from "@/app/core/service/my-little-saas-application/error/api-error.service";
import { Router } from "@angular/router";

@Injectable()
export class ApiService {

  constructor(
    private http: HttpClient,
    private apiErrorService: ApiErrorService,
    private router: Router
  ) {}


  private formatErrors(error: HttpErrorResponse) {
    this.apiErrorService.currentError = error
    this.router.navigate(["/error/"],{})
      .then()
    return  throwError(error.error);
  }

  get(path: string, params: HttpParams = new HttpParams()): Observable<any> {
    return this.http.get(`${path}`, { params })
      .pipe(catchError( err => this.formatErrors(err)));
  }

  put(path: string, body: Object = {}): Observable<any> {
    return this.http.put(
      `${path}`,
      JSON.stringify(body)
    ).pipe(catchError( err => this.formatErrors(err)));
  }

  post(path: string, body: Object = {}): Observable<any> {
    return this.http.post(
      `${path}`,
      JSON.stringify(body)
    ).pipe(catchError( err => this.formatErrors(err)));
  }

  delete(path: string): Observable<any> {
    return this.http.delete(
      `${path}`
    ).pipe(catchError( err => this.formatErrors(err)));
  }
}
