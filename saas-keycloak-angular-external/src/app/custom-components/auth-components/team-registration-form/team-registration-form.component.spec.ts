import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamRegistrationFormComponent } from './team-registration-form.component';

describe('TeamRegistrationFormComponent', () => {
  let component: TeamRegistrationFormComponent;
  let fixture: ComponentFixture<TeamRegistrationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamRegistrationFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamRegistrationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
