import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { FormBuilder, FormGroup } from '@angular/forms';
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
  filterForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private movieService: MovieService,
    private activedRoute: ActivatedRoute,
    private toast: NgToastService
  ) { }

  ngOnInit(): void {
    this.filterForm = this.fb.group({
      title: [''],
      actor: [''],
      director: [''],
      writer: [''],
      genre: [''],
      minPrice: [''],
      maxPrice: ['']
    });
    this.loadMovies();
  }

  loadMovies(): void {
    this.activedRoute.queryParams.subscribe(params => {
      if (params['page'] && params['size']) {
        this.pageReq = {
          page: Number(params['page']),
          size: Number(params['size'])
        };
      } else {
        this.pageReq = {
          page: 0,
          size: 10
        };
      }

      const filterParams = this.filterForm.value;

      this.movieService.getAllMovies(this.pageReq, filterParams).subscribe(res => {
        this.pageInfo = new PageInfo(res);
        this.movies = res.content;
      });
    });
  }

  onDelete(id: number): void {
    if (confirm('Do you want to delete this movie?')) {
      this.movieService.deleteMovie(id).subscribe({
        next: () => {
          this.toast.success({ detail: 'Success', summary: 'Movie deleted successfully', duration: 3000 });
          this.loadMovies();
        },
        error: err => {
          this.toast.error({ detail: 'Error', summary: 'Failed to delete movie', duration: 3000 });
        }
      });
    }
  }

  applyFilters(): void {
    const filterParams = this.filterForm.value;

    this.movieService.getAllMovies(this.pageReq, filterParams).subscribe(res => {
      this.pageInfo = new PageInfo(res);
      this.movies = res.content;
    });
  }

  resetFilters(): void {
    this.filterForm.reset();
    this.applyFilters();
  }
}
