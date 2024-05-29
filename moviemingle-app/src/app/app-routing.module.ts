import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { PublicLayoutComponent } from './layouts/public-layout/public-layout.component';
import { HomeComponent } from './pages/home/home.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { authGuard } from './guards/auth.guard';
import { UserAdminComponent } from './pages/admin-user/user-admin.component';
import { UserDetailComponent } from './pages/user-detail/user-detail.component';
import { UserLayoutComponent } from './layouts/user-layout/user-layout.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { PaymentResultComponent } from './pages/payment-result/payment-result.component';
import { UserMovieComponent } from './pages/user-movie/user-movie.component';

const routes: Routes = [
  {
    path: '',
    component: PublicLayoutComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent
      },
      {
        path: 'user',
        component: UserLayoutComponent,
        canActivate: [authGuard],
        data: { roles: ['ADMIN', 'USER'] },
        children: [
          {
            path: 'profile',
            component: ProfileComponent
          },
          {
            path: 'movies',
            component: UserMovieComponent
          }
        ]
      },
      {
        path: 'payment',
        component: PaymentResultComponent
      }
    ]
  },
  {
    path: 'admin',
    component: AdminLayoutComponent,
    canActivate: [authGuard],
    data: { roles: ['ADMIN'] },
    children: [
      {
        path: 'user',
        component: UserAdminComponent,
      },
      {
        path: 'user/:id',
        component: UserDetailComponent
      }
    ]
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
