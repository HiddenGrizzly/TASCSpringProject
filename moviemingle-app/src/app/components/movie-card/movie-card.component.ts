import { Component, Input } from '@angular/core';
import { MovieDto } from 'src/app/models/movies/MovieDto';
import { CartService } from 'src/app/services/cart/cart.service';

@Component({
  selector: 'app-movie-card',
  templateUrl: './movie-card.component.html',
  styleUrls: ['./movie-card.component.css']
})
export class MovieCardComponent {

  @Input() movie!: MovieDto;

  constructor(private cartService: CartService) {

  }

  addToCart(movieId: number) {
    this.cartService.addToCart(movieId);
  }

}
