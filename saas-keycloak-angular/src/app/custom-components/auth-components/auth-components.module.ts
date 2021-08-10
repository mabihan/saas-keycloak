import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeamRegistrationComponent } from './team-registration/team-registration.component';
import { TeamRegistrationFormComponent } from './team-registration-form/team-registration-form.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NzFormModule } from "ng-zorro-antd/form";
import { NzInputModule } from "ng-zorro-antd/input";
import { NzButtonModule } from "ng-zorro-antd/button";
import { NzSelectModule } from "ng-zorro-antd/select";
import { NzStepsModule } from "ng-zorro-antd/steps";
import { TeamRegistrationStep001Component } from './team-registration-step001/team-registration-step001.component';
import { TeamRegistrationStep002Component } from './team-registration-step002/team-registration-step002.component';
import { TeamRegistrationStep003Component } from './team-registration-step003/team-registration-step003.component';
import { NzTabsModule } from "ng-zorro-antd/tabs";
import { TeamRegistrationStep004Component } from './team-registration-step004/team-registration-step004.component';
import { NzResultModule } from "ng-zorro-antd/result";
import { NzSpinModule } from "ng-zorro-antd/spin";
import { NzProgressModule } from "ng-zorro-antd/progress";
import { PasswordStrengthMeterModule } from "angular-password-strength-meter";



@NgModule({
    declarations: [
        TeamRegistrationComponent,
        TeamRegistrationFormComponent,
        TeamRegistrationStep001Component,
        TeamRegistrationStep002Component,
        TeamRegistrationStep003Component,
        TeamRegistrationStep004Component
    ],
    exports: [
        TeamRegistrationComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        NzFormModule,
        NzInputModule,
        NzButtonModule,
        NzSelectModule,
        FormsModule,
        NzStepsModule,
        NzTabsModule,
        NzResultModule,
        NzSpinModule,
        NzProgressModule,
        PasswordStrengthMeterModule
    ]
})
export class AuthComponentsModule { }
