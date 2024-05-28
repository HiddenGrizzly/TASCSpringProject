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
import { UserAdminComponent } from './pages/user-admin/user-admin.component';
import { UserDetailComponent } from './pages/user-detail/user-detail.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { GoBackBtnComponent } from './components/go-back-btn/go-back-btn.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { UserLayoutComponent } from './layouts/user-layout/user-layout.component';
import { NgToastModule } from 'ng-angular-popup';
import { UserDropdownComponent } from './components/user-dropdown/user-dropdown.component';
import { ModalComponent } from './components/modal/modal.component';
import { ChangePasswordFormComponent } from './components/change-password-form/change-password-form.component';

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
    ChangePasswordFormComponent
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
