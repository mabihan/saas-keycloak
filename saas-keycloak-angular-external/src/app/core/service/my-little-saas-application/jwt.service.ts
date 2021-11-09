import { Injectable } from '@angular/core';


@Injectable()
export class JwtService {

  getToken(): String {
    return window.localStorage['access_token'];
  }

  saveToken(token: String) {
    window.localStorage['access_token'] = token;
  }

  destroyToken() {
    window.localStorage.removeItem('access_token');
  }
}
