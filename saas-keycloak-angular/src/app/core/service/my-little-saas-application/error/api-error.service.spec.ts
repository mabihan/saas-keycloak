import { TestBed } from '@angular/core/testing';

import { ApiErrorService } from './api-error.service';

describe('ApiErrorService', () => {
  let service: ApiErrorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiErrorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
