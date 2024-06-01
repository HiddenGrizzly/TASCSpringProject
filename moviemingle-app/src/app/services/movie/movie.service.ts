import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MovieDto } from 'src/app/models/movies/MovieDto';
import { MovieRes } from 'src/app/models/movies/MovieRes';
import { PageReq } from 'src/app/models/pages/PageReq';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private readonly apiUrl = 'movies';

  constructor(private http: HttpClient) { }

  getAllMovies(page: PageReq | null): Observable<any> {
    return this.http.get(this.apiUrl, {
      params: { ...page }
    });
  }

  getMovie(id: string): Observable<MovieRes> {
    return this.http.get<MovieRes>(`${this.apiUrl}/${id}`);
  }

addMovie(movie: MovieDto): Observable<MovieDto> {
  return this.http.post<MovieDto>(`${this.apiUrl}/omdb`, movie);
}

updateMovie(movie: MovieDto): Observable<MovieDto> {
  return this.http.put<MovieDto>(`${this.apiUrl}/${movie.id}`, movie);
}

deleteMovie(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${id}`);
}

  getById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  getMoviesByIds(ids: number[]): Observable<any> {
    return this.http.get(`${this.apiUrl}/lists`, {
      params: { ids: ids }
    })
  }
}
