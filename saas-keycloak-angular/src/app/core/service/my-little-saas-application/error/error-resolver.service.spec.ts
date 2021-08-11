import { TestBed } from '@angular/core/testing';

import { ErrorResolverService } from './error-resolver.service';

describe('ErrorResolverService', () => {
  let service: ErrorResolverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ErrorResolverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
