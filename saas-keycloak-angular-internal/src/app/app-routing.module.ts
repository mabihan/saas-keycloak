import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuardService } from "@/app/core/service/auth/auth-guard.service";
const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/app/home' },
  {
    path: 'app', loadChildren: () => import('./pages/webapp/webapp.module').then(m => m.WebappModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'error', loadChildren: () => import('./pages/error/error.module').then(m => m.ErrorModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
