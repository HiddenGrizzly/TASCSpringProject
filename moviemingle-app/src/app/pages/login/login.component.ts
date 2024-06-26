import { Location } from '@angular/common';
import { LoginReq } from './../../models/auth/LoginReq';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AuthState } from 'src/app/models/auth/AuthState';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder,
    private location: Location
  ) {

  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const LoginReq = this.loginForm.value;
      this.authService.login(LoginReq).subscribe({
        next: res => {
          const authState: AuthState = res;
          this.authService.setAuthState(authState);
          this.location.back();
        },
        error: err => {
          console.log(err);
        }
      });
    }
  }

}
