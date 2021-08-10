import { TestBed } from '@angular/core/testing';

import { KeycloakUserService } from './keycloak-user.service';

describe('KeycloakUserService', () => {
  let service: KeycloakUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KeycloakUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
