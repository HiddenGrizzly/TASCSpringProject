import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieRes } from 'src/app/models/movies/MovieRes';
import { ModalService } from 'src/app/services/modal/modal.service';
import { MovieService } from 'src/app/services/movie/movie.service';

@Component({
  selector: 'app-movie-detail-admin',
  templateUrl: './movie-detail-admin.component.html',
  styleUrls: ['./movie-detail-admin.component.css']
})
export class MovieDetailAdminComponent implements OnInit {

  movie?: MovieRes;


  constructor(
    private movieService: MovieService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if(params['id']){
        this.movieService.getById(Number(params['id'])).subscribe(res =>{
          console.log(res);
          this.movie = res;
        })
      }
    })
  }
}