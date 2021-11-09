import { TestBed } from '@angular/core/testing';

import { KeycloakInitializerService } from './keycloak-initializer.service';

describe('KeycloakInitializerService', () => {
  let service: KeycloakInitializerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KeycloakInitializerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
