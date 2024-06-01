import { SelfService } from 'src/app/services/self/self.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieDto } from 'src/app/models/movies/MovieDto';
import { PageInfo } from 'src/app/models/pages/PageInfo';
import { PageReq } from 'src/app/models/pages/PageReq';
import { MovieService } from 'src/app/services/movie/movie.service';

@Component({
  selector: 'app-user-movie',
  templateUrl: './user-movie.component.html',
  styleUrls: ['./user-movie.component.css']
})
export class UserMovieComponent implements OnInit {

  movies!: MovieDto[];

  pageReq: PageReq | null = null;

  pageInfo!: PageInfo;

  constructor(
    private activatedRoute: ActivatedRoute,
    private selfService: SelfService
  ) { }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      if (params['page'] && params['size']) {
        this.pageReq = {
          page: Number(params['page']),
          size: Number(params['size'])
        }
      }
      this.selfService.getUserMovies(this.pageReq).subscribe(res => {
        console.log(res);
        this.pageInfo = new PageInfo(res);
        this.movies = res.content;
      })
    })
  }
}
