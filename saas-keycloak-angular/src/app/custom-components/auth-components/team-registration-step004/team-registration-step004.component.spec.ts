import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamRegistrationStep004Component } from './team-registration-step004.component';

describe('TeamRegistrationStep004Component', () => {
  let component: TeamRegistrationStep004Component;
  let fixture: ComponentFixture<TeamRegistrationStep004Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamRegistrationStep004Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamRegistrationStep004Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
