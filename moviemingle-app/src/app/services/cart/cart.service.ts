import { NgToastService } from 'ng-angular-popup';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  cartSubject$: BehaviorSubject<number[]>;

  constructor(private authService: AuthService, private toast: NgToastService, private router: Router) {
    this.cartSubject$ = new BehaviorSubject(this.getCart());
  }

  // get cart
  getCart(): number[] {
    const cartItems = localStorage.getItem('cartItems');
    return cartItems ? JSON.parse(cartItems) : [];
  }

  // set cart
  setCart(items: number[]) {
    const cartJson = JSON.stringify(items);
    localStorage.setItem('cartItems', cartJson);
    this.cartSubject$.next(items);
  }

  // observe cart
  observeCart(): Observable<number[]> {
    return this.cartSubject$.asObservable();
  }

  // add an item to cart
  addToCart(movieId: number) {
    if (!this.authService.getAuthState()) {
      this.toast.info({ detail: "Please login first" });
      this.router.navigateByUrl('/login');
    } else {
      if (this.getCart().includes(movieId)) {
        this.toast.warning({ detail: "Remind", summary: "Movie already in your cart" });
      } else {
        const items = [...this.getCart(), movieId];
        this.setCart(items);
      }
    }
  }

  // remove item from cart
  removeFromCart(movieId: number) {
    const items = this.getCart().filter(item => item !== movieId);
    this.setCart(items);
  }

  // clear cart
  clearCart() {
    this.setCart([]);
  }
}
