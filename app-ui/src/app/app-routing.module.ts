import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/_auth/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './_helpers/guards/auth.guard';
import { RegisterComponent } from './components/_auth/register/register.component';
import { AccountComponent } from './components/account/account.component';
import { RailwayAddComponent } from './components/railway-add/railway-add.component';
import { RailwayEditComponent } from './components/railway-edit/railway-edit.component';
import { OrderAddComponent } from './components/order/order-add/order-add.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'account', component: AccountComponent, canActivate: [AuthGuard] },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'order-add/:id', component: OrderAddComponent, canActivate: [AuthGuard] },
  { path: 'railway-add', component: RailwayAddComponent, canActivate: [AuthGuard] },
  { path: 'railway-edit/:id', component: RailwayEditComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
