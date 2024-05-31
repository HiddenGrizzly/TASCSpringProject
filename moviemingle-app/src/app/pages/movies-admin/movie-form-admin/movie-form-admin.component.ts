import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { MovieService } from 'src/app/services/movie/movie.service';

@Component({
  selector: 'app-movie-form-admin',
  templateUrl: './movie-form-admin.component.html',
  styleUrls: ['./movie-form-admin.component.css']
})
export class MovieFormAdminComponent implements OnInit {
  movieForm!: FormGroup;
  constructor(
    private movieService: MovieService,
    private formBuilder: FormBuilder,
    private router: Router,
    private toast: NgToastService
  ) { }

  ngOnInit():void {
    this.movieForm = this.formBuilder.group({
      title: ['', Validators.required],
      trailer: [''],
      price: [0, Validators.min(0)]
    })
  }
  onSubmit() {
    if (this.movieForm.valid){
      this.movieService.addMovie(this.movieForm.value).subscribe({
        next: res => {
          this.router.navigateByUrl('/admin/movies');
          this.toast.success({
            detail: 'Success',
            summary: 'Movie created.'
          })
        },
        error: err => {
          console.log(err);
          this.toast.error({
            detail: 'Error',
            summary: err.error
          })
        }
      })
    }
  }
}
