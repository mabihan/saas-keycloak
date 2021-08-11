import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLoginStep001Component } from './user-login-step001.component';

describe('UserLoginStep001Component', () => {
  let component: UserLoginStep001Component;
  let fixture: ComponentFixture<UserLoginStep001Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserLoginStep001Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserLoginStep001Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
