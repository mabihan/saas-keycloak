import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamRegistrationStep001Component } from './team-registration-step001.component';

describe('TeamRegistrationStep001Component', () => {
  let component: TeamRegistrationStep001Component;
  let fixture: ComponentFixture<TeamRegistrationStep001Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamRegistrationStep001Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamRegistrationStep001Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
