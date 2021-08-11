import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from "./core/guard/auth.guard";

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/welcome/home' },
  { path: 'auth', loadChildren: () => import('./pages/auth/auth.module').then(m => m.AuthModule) },
  { path: 'welcome', loadChildren: () => import('./pages/welcome/welcome.module').then(m => m.WelcomeModule) },
  { path: 'app', loadChildren: () => import('./pages/webapp/webapp.module').then(m => m.WebappModule), canActivate: [AuthGuard] },
  { path: 'error', loadChildren: () => import('./pages/error/error.module').then(m => m.ErrorModule) },
  { path: 'boarding', loadChildren: () => import('./pages/boarding/boarding.module').then(m => m.BoardingModule) },
  { path: 'roadmap', loadChildren: () => import('./pages/roadmap/roadmap.module').then(m => m.RoadmapModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
