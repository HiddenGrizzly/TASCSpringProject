import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieRes } from 'src/app/models/movies/MovieRes';
import { PageInfo } from 'src/app/models/pages/PageInfo';
import { PageReq } from 'src/app/models/pages/PageReq';
import { MovieService } from 'src/app/services/movie/movie.service';

@Component({
  selector: 'app-movie-list-admin',
  templateUrl: './movie-list-admin.component.html',
  styleUrls: ['./movie-list-admin.component.css']
})
export class MovieListAdminComponent implements OnInit {
  movies: MovieRes[] = [];
  pageReq: PageReq | null = null;
  pageInfo!: PageInfo;

  constructor(
    private movieService: MovieService,
    private activedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.activedRoute.queryParams.subscribe(params => {
      if (params['page'] && params['size']) {
        this.pageReq = {
          page: Number(params['page']),
          size: Number(params['size'])
        }
      }

      this.movieService.getAllMovies(this.pageReq).subscribe(res => {
        console.log(this.pageReq)
        this.pageInfo = new PageInfo(res);
        this.movies = res.content;
        console.log(res);
      });
    })
  }

}
