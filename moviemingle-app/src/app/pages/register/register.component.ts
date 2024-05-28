import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterReq } from 'src/app/models/auth/RegisterReq';
import { AuthService } from 'src/app/services/auth/auth.service';
import { matchPassword } from 'src/app/utils/ValidationUtils';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder
  ) {

  }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      reTypePassword: ['', [Validators.required]]
    },
      {
        validator: matchPassword('password', 'reTypePassword')
      }
    );
  }



  onSubmit() {
    if (this.registerForm.valid) {
      const formValues = { ...this.registerForm.value };
      delete formValues.reTypePassword;
      const registerReq: RegisterReq = formValues;
      this.authService.register(registerReq).subscribe({
        next: res => {
          this.router.navigateByUrl('/');
        },
        error: err => {
          console.log(err);
        }
      });
    }
  }

}
