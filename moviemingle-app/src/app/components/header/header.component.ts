import { AuthService } from 'src/app/services/auth/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private AuthService: AuthService) {

  }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  onLogout(): void {
    this.AuthService.logout();
  }

}
