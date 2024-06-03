import { AuthState } from './../../models/auth/AuthState';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart/cart.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  authState!: AuthState | null;

  cart: number[] = [];

  constructor(
    private authService: AuthService,
    private cartService: CartService
  ) {
  }
  ngOnInit(): void {
    this.authService.observeAuthState().subscribe(res => {
      this.authState = res;
      this.cartService.observeCart().subscribe(res => {
        console.log(res);
        this.cart = res;
      })
    })
  }



}
