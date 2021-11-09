import { TestBed } from '@angular/core/testing';

import { TimezonesService } from './timezones.service';

describe('TimezonesService', () => {
  let service: TimezonesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TimezonesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
