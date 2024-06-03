import { SelfService } from './../../services/self/self.service';
import { Component, OnInit } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { Cart } from 'src/app/models/cart/Cart';
import { CartItem } from 'src/app/models/cart/CartItem';
import { MovieDto } from 'src/app/models/movies/MovieDto';
import { CartService } from 'src/app/services/cart/cart.service';
import { MovieService } from 'src/app/services/movie/movie.service';
import { fromArrayToStringWithComma } from 'src/app/utils/StringUtils';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  movies!: MovieDto[];

  total: number = 0;

  constructor(
    private cartService: CartService,
    private movieService: MovieService,
    private toast: NgToastService,
    private selfService: SelfService
  ) { }

  ngOnInit(): void {
    this.cartService.observeCart().subscribe(params => {
      const ids = params;
      this.movieService.getMoviesByIds(ids).subscribe(res => {
        this.movies = res;
        this.total = this.movies.reduce((p, n) => p + n.price, 0);
      });
    })
  }

  removeMovie(movieId: number) {
    this.cartService.removeFromCart(movieId);
  }

  convertToString(arr: any) {
    return fromArrayToStringWithComma(arr);
  }

  submitCart() {
    if (this.cartService.getCart().length === 0) {
      this.toast.warning({ detail: 'Blank Cart' });
    } else {
      let cart = new Cart();
      cart.paymentMethod = "STRIPE";
      let items: CartItem[] = [];
      for (let movie of this.movies) {
        let item = new CartItem();
        item.movieId = movie.id!;
        item.purchasePrice = movie.price;
        items.push(item);
      }
      cart.items = items;
      this.selfService.createUserOrder(cart).subscribe(res => {
        this.cartService.clearCart();
        window.location.replace(res.paymentUrl);
      })
    }
  }

}
