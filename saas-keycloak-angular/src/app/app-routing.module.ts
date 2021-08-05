import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/welcome/home' },
  { path: 'auth', loadChildren: () => import('./pages/auth/auth.module').then(m => m.AuthModule) },
  { path: 'welcome', loadChildren: () => import('./pages/welcome/welcome.module').then(m => m.WelcomeModule) },
  { path: 'app', loadChildren: () => import('./pages/webapp/webapp.module').then(m => m.WebappModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
