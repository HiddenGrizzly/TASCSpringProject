import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { PublicLayoutComponent } from './layouts/public-layout/public-layout.component';
import { HomeComponent } from './pages/home/home.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { authGuard } from './guards/auth.guard';
import { UserDetailComponent } from './pages/user-detail/user-detail.component';
import { UserLayoutComponent } from './layouts/user-layout/user-layout.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { PaymentResultComponent } from './pages/payment-result/payment-result.component';
import { UserMovieComponent } from './pages/user-movie/user-movie.component';
import { MovieListAdminComponent } from './pages/movies-admin/movie-list-admin/movie-list-admin.component';
import { MovieFormAdminComponent } from './pages/movies-admin/movie-form-admin/movie-form-admin.component';
import { MovieDetailAdminComponent } from './pages/movies-admin/movie-detail-admin/movie-detail-admin.component';
import { UserAdminComponent } from './pages/user-admin/user-admin.component';
import { OrderComponent } from './pages/order/order.component';
import { OrderdetailComponent } from './pages/orderdetail/orderdetail.component';

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
      },
      {
        path: 'movies',
        children: [
          {
            path: '',
            component: MovieListAdminComponent
          },
          {
            path: 'add',
            component: MovieFormAdminComponent
          },
          {
            path: 'edit/:id',
            component: MovieFormAdminComponent
          },
          {
            path: ':id',
            component: MovieDetailAdminComponent
          }
        ]
      },
      {
        path: 'order',
        component: OrderComponent
      },
      {
        path: 'order/:id',
        component: OrderdetailComponent
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
