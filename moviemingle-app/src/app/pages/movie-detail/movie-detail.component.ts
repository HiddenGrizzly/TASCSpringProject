import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieDto } from 'src/app/models/movies/MovieDto';
import { CartService } from 'src/app/services/cart/cart.service';
import { MovieService } from 'src/app/services/movie/movie.service';
import { fromArrayToStringWithComma } from 'src/app/utils/StringUtils';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.css']
})
export class MovieDetailComponent implements OnInit {

  movie!: MovieDto;

  trailer!: string;

  constructor(
    private movieService: MovieService,
    private activatedRoute: ActivatedRoute,
    private cartService: CartService
  ) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (params['id']) {
        this.movieService.getById(Number(params['id'])).subscribe(res => {
          console.log(res);
          this.movie = res;
          this.trailer = res.trailer;
        })
      }
    })
  }

  addToCart(movieId: number) {
    this.cartService.addToCart(movieId);
  }

  convertArr(arr: any) {
    return fromArrayToStringWithComma(arr);
  }

}
