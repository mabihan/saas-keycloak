import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoadmapComponent } from "@/app/pages/roadmap/roadmap.component";

const routes: Routes = [
  { path: '', component: RoadmapComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoadmapRoutingModule { }
