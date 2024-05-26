import { AuthService } from 'src/app/services/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { AuthState } from 'src/app/models/auth/AuthState';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  authState!: AuthState;

  constructor(private authService: AuthService) {

  }

  ngOnInit(): void {
    this.authService.observeAuthState().subscribe(res => {
      res && (this.authState = res);
    })
  }

  onLogout(): void {
    this.authService.logout();
  }

}
