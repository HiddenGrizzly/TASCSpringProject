import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieDto } from 'src/app/models/movies/MovieDto';
import { MovieService } from 'src/app/services/movie/movie.service';

@Component({
  selector: 'app-movie-form-update-admin',
  templateUrl: './movie-form-update-admin.component.html',
  styleUrls: ['./movie-form-update-admin.component.css']
})
export class MovieFormUpdateAdminComponent implements OnInit {
  movieUpdateForm!: FormGroup;
  movieId!: number;

  constructor(
    private fb: FormBuilder,
    private movieService: MovieService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.movieUpdateForm = this.fb.group({
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

    this.route.params.subscribe(params => {
      this.movieId = params['id'];
      if (this.movieId) {
        this.movieService.getMovie(this.movieId).subscribe(movie => {
          this.movieUpdateForm.patchValue(movie);
          this.setFormArray('writers', movie.writers);
          this.setFormArray('actors', movie.actors);
          this.setFormArray('directors', movie.directors);
          this.setFormArray('genres', movie.genres);
        });
      }
    });
  }

  get writers(): FormArray {
    return this.movieUpdateForm.get('writers') as FormArray;
  }

  get actors(): FormArray {
    return this.movieUpdateForm.get('actors') as FormArray;
  }

  get directors(): FormArray {
    return this.movieUpdateForm.get('directors') as FormArray;
  }

  get genres(): FormArray {
    return this.movieUpdateForm.get('genres') as FormArray;
  }

  setFormArray(controlName: string, values: Set<string>): void {
    const formArray = this.movieUpdateForm.get(controlName) as FormArray;
    formArray.clear();
    values.forEach(value => formArray.push(this.fb.control(value, Validators.required)));
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
    if (this.movieUpdateForm.valid) {
      const formValue = this.movieUpdateForm.value;
      const updatedMovie: MovieDto = {
        ...formValue,
        id: this.movieId, 
        writers: Array.from(formValue.writers),
        actors: Array.from(formValue.actors),
        directors: Array.from(formValue.directors),
        genres: Array.from(formValue.genres)
      };
  
      console.log('Updated Movie:', updatedMovie);
  
      const observer = {
        next: (response: any) => {
          console.log('Movie updated successfully:', response);
          this.router.navigate(['/admin/movies']);
        },
        error: (error: any) => {
          console.error('Error updating movie:', error);
        },
        complete: () => {
          console.log('Update complete');
        }
      };
  
      this.movieService.updateMovie(updatedMovie).subscribe(observer);
    }
  }  
}
