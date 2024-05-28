import { Component, Input } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-user-dropdown',
  templateUrl: './user-dropdown.component.html',
  styleUrls: ['./user-dropdown.component.css']
})
export class UserDropdownComponent {
  @Input() avatar?: string;

  constructor(private authService: AuthService) { }

  onLogout() {
    this.authService.logout();
  }

}
