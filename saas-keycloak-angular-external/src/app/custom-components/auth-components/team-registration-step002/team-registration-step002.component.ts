import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { UserService } from "@/app/core/service/my-little-saas-application/user/user.service";

@Component({
  selector: 'app-team-registration-step002',
  templateUrl: './team-registration-step002.component.html',
  styleUrls: ['./team-registration-step002.component.scss']
})
export class TeamRegistrationStep002Component implements OnInit {

  @Input() validateForm: FormGroup
  @Output() passwordStrengthChange = new EventEmitter();

  password: string;

  constructor(private fb: FormBuilder, private userService: UserService) {
    this.password = ""
    this.validateForm = fb.group({})
  }

  ngOnInit(): void {
  }

  onStrengthChange($event: number) {
    this.passwordStrengthChange.emit($event)
  }

  onPasswordChange($event: any) {
    this.password = $event
  }
}
