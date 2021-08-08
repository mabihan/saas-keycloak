import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamRegistrationStep002Component } from './team-registration-step002.component';

describe('TeamRegistrationStep002Component', () => {
  let component: TeamRegistrationStep002Component;
  let fixture: ComponentFixture<TeamRegistrationStep002Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamRegistrationStep002Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamRegistrationStep002Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
