import { AuthState } from './../../models/auth/AuthState';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  authState!: AuthState | null;

  constructor(private authService: AuthService) {
  }
  ngOnInit(): void {
    this.authService.observeAuthState().subscribe(res => {
      this.authState = res;
    })
  }

}
