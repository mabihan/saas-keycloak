import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoardingComponent } from "@/app/pages/boarding/boarding.component";

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'start' },
  { path: 'start', component: BoardingComponent },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BoardingRoutingModule { }
