import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamRegistrationStep003Component } from './team-registration-step003.component';

describe('TeamRegistrationStep003Component', () => {
  let component: TeamRegistrationStep003Component;
  let fixture: ComponentFixture<TeamRegistrationStep003Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamRegistrationStep003Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamRegistrationStep003Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
