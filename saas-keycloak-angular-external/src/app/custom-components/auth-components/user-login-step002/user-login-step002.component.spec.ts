import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLoginStep002Component } from './user-login-step002.component';

describe('UserLoginStep002Component', () => {
  let component: UserLoginStep002Component;
  let fixture: ComponentFixture<UserLoginStep002Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserLoginStep002Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserLoginStep002Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
