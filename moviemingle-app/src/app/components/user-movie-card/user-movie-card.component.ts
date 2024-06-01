import { Component, Input } from '@angular/core';
import { MovieDto } from 'src/app/models/movies/MovieDto';

@Component({
  selector: 'app-user-movie-card',
  templateUrl: './user-movie-card.component.html',
  styleUrls: ['./user-movie-card.component.css']
})
export class UserMovieCardComponent {
  @Input() movie!: MovieDto;

}
