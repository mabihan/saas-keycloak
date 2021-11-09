import { Component, Input, OnInit } from '@angular/core';
import { UserResponse } from "@/app/core/model/api/api";

@Component({
  selector: 'app-team-registration-step004',
  templateUrl: './team-registration-step004.component.html',
  styleUrls: ['./team-registration-step004.component.scss']
})
export class TeamRegistrationStep004Component implements OnInit {

  @Input() loading: boolean
  @Input() createdUser: UserResponse

  constructor() {
    this.loading = true
    this.createdUser = {
      uuid: "",
      tenantUuid: "",
      firstName: "",
      lastName: "",
      email: "",
      username: "",
      createdTimestamp: new Date(),
      enabled: false,
      totp: false,
      emailVerified: false
    }
  }

  ngOnInit(): void {
  }

}
