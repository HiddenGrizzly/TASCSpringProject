import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieDto } from 'src/app/models/movies/MovieDto';
import { PageInfo } from 'src/app/models/pages/PageInfo';
import { PageReq } from 'src/app/models/pages/PageReq';
import { MovieService } from 'src/app/services/movie/movie.service';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  movies!: MovieDto[];

  pageReq: PageReq | null = null;

  pageInfo!: PageInfo;

  constructor(
    private movieService: MovieService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      if (params['page'] && params['size']) {
        this.pageReq = {
          page: Number(params['page']),
          size: Number(params['size'])
        }
      }
      this.movieService.getAllMovies(this.pageReq).subscribe(res => {
        this.pageInfo = new PageInfo(res);
        this.movies = res.content;
      })
    })
  }
}
