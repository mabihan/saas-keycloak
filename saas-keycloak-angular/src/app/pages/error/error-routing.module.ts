import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GenericErrorComponent } from "@/app/pages/error/generic-error/generic-error.component";
import { ErrorResolverService } from "@/app/core/service/my-little-saas-application/error/error-resolver.service";

const routes: Routes = [
  { path: '',
    component: GenericErrorComponent,
    resolve: {
      error: ErrorResolverService
    }}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ErrorRoutingModule { }
