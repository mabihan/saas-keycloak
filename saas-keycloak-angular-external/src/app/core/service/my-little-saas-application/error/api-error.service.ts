import { Injectable } from '@angular/core';
import { HttpErrorResponse } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ApiErrorService {

  private _currentError: HttpErrorResponse

  constructor() {
    this._currentError = new HttpErrorResponse({})
  }

  get currentError(): HttpErrorResponse {
    return this._currentError;
  }

  set currentError(value: HttpErrorResponse) {
    this._currentError = value;
  }
}
