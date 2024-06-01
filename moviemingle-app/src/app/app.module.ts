import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { JwtModule } from '@auth0/angular-jwt';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { PublicLayoutComponent } from './layouts/public-layout/public-layout.component';
import { HomeComponent } from './pages/home/home.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HeaderComponent } from './components/header/header.component';
import { BaseInterceptor } from './interceptors/base.interceptor';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { UserDetailComponent } from './pages/user-detail/user-detail.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { GoBackBtnComponent } from './components/go-back-btn/go-back-btn.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { UserLayoutComponent } from './layouts/user-layout/user-layout.component';
import { NgToastModule } from 'ng-angular-popup';
import { UserDropdownComponent } from './components/user-dropdown/user-dropdown.component';
import { ModalComponent } from './components/modal/modal.component';
import { ChangePasswordFormComponent } from './components/change-password-form/change-password-form.component';
import { PaymentResultComponent } from './pages/payment-result/payment-result.component';
import { UserMovieComponent } from './pages/user-movie/user-movie.component';
import { MovieListAdminComponent } from './pages/movies-admin/movie-list-admin/movie-list-admin.component';
import { MovieFormAdminComponent } from './pages/movies-admin/movie-form-admin/movie-form-admin.component';
import { MovieDetailAdminComponent } from './pages/movies-admin/movie-detail-admin/movie-detail-admin.component';
import { UserAdminComponent } from './pages/user-admin/user-admin.component';
import { OrderComponent } from './pages/order/order.component';
import { OrderdetailComponent } from './pages/orderdetail/orderdetail.component';
import { UtubePlayerComponent } from './components/utube-player/utube-player.component';
import { UserMovieCardComponent } from './components/user-movie-card/user-movie-card.component';
import { MovieListComponent } from './pages/movies/movie-list/movie-list.component';
import { MovieCardComponent } from './components/movie-card/movie-card.component';
import { MovieDetailComponent } from './pages/movie-detail/movie-detail.component';
import { CartComponent } from './pages/cart/cart.component';
import { PageNotfoundComponent } from './pages/page-notfound/page-notfound.component';
import { MovieFormUpdateAdminComponent } from './pages/movies-admin/movie-form-update-admin/movie-form-update-admin.component';

@NgModule({
  declarations: [
    AppComponent,
    PublicLayoutComponent,
    AdminLayoutComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    SidebarComponent,
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
    UserAdminComponent,
    UserDetailComponent,
    PaginationComponent,
    GoBackBtnComponent,
    ProfileComponent,
    UserLayoutComponent,
    UserDropdownComponent,
    ModalComponent,
    ChangePasswordFormComponent,
    PaymentResultComponent,
    UserMovieComponent,
    MovieListAdminComponent,
    MovieFormAdminComponent,
    MovieDetailAdminComponent,
    UserMovieComponent,
    OrderComponent,
    OrderdetailComponent,
    UtubePlayerComponent,
    UserMovieCardComponent,
    MovieListComponent,
    MovieCardComponent,
    MovieDetailComponent,
    CartComponent,
    PageNotfoundComponent,
    MovieFormUpdateAdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgToastModule,
    ReactiveFormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          const authState = localStorage.getItem('authState');

          if (!authState) return null;

          const token = JSON.parse(authState).accessToken;
          return !token ? null : token;
        },
      },
    }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BaseInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
