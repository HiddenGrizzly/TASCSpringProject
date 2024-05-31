import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MovieDto } from 'src/app/models/movies/MovieDto';
import { MovieService } from 'src/app/services/movie/movie.service';

@Component({
  selector: 'app-movie-form-update-admin',
  templateUrl: './movie-form-update-admin.component.html',
  styleUrls: ['./movie-form-update-admin.component.css']
})
export class MovieFormUpdateAdminComponent implements OnInit {
  movieForm!: FormGroup;

  constructor(private fb: FormBuilder, private movieService: MovieService) {}

  ngOnInit(): void {
    this.movieForm = this.fb.group({
      movieTitle: ['', [Validators.required, Validators.minLength(1)]],
      year: ['', [Validators.required, Validators.pattern('^[0-9]{4}$')]],
      imdbId: ['', [Validators.required, Validators.pattern('^tt[0-9]+$')]],
      rated: ['', [Validators.required, Validators.minLength(1)]],
      released: ['', [Validators.required, Validators.pattern('^[0-9]{4}-[0-9]{2}-[0-9]{2}$')]],
      runtime: ['', [Validators.required, Validators.minLength(1)]],
      plot: ['', [Validators.required, Validators.minLength(1)]],
      awards: ['', [Validators.required, Validators.minLength(1)]],
      poster: ['', [Validators.required, Validators.minLength(1)]],
      trailer: [''],
      price: ['', [Validators.required, Validators.min(0)]],
      writers: this.fb.array([], [Validators.required]),
      actors: this.fb.array([], [Validators.required]),
      directors: this.fb.array([], [Validators.required]),
      genres: this.fb.array([], [Validators.required])
    });
  }

  get writers(): FormArray {
    return this.movieForm.get('writers') as FormArray;
  }

  get actors(): FormArray {
    return this.movieForm.get('actors') as FormArray;
  }

  get directors(): FormArray {
    return this.movieForm.get('directors') as FormArray;
  }

  get genres(): FormArray {
    return this.movieForm.get('genres') as FormArray;
  }

  addWriter(): void {
    this.writers.push(this.fb.control('', Validators.required));
  }

  removeWriter(index: number): void {
    this.writers.removeAt(index);
  }

  addActor(): void {
    this.actors.push(this.fb.control('', Validators.required));
  }

  removeActor(index: number): void {
    this.actors.removeAt(index);
  }

  addDirector(): void {
    this.directors.push(this.fb.control('', Validators.required));
  }

  removeDirector(index: number): void {
    this.directors.removeAt(index);
  }

  addGenre(): void {
    this.genres.push(this.fb.control('', Validators.required));
  }

  removeGenre(index: number): void {
    this.genres.removeAt(index);
  }

  onSubmit(): void {
    if (this.movieForm.valid) {
      const formValue = this.movieForm.value;
      const updatedMovie: MovieDto = {
        ...formValue,
        writers: new Set(formValue.writers),
        actors: new Set(formValue.actors),
        directors: new Set(formValue.directors),
        genres: new Set(formValue.genres)
      };
      console.log('Updated Movie:', updatedMovie);
      this.movieService.updateMovie(updatedMovie).subscribe(
        response => {
          console.log('Movie updated successfully:', response);
        },
        error => {
          console.error('Error updating movie:', error);
        }
      );
    }
  }
}
