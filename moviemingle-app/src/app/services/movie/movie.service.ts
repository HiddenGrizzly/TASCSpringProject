import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MovieAdd } from 'src/app/models/movies/MovieAdd';
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
    params: {...page}
  });
}

getMovie(id: string): Observable<MovieRes> {
  return this.http.get<MovieRes>(`${this.apiUrl}/${id}`);
}

addMovie(movie: MovieAdd): Observable<MovieAdd> {
  return this.http.post<MovieAdd>(`${this.apiUrl}/omdb`, movie);
}

updateMovie(movie: MovieAdd): Observable<MovieAdd> {
  return this.http.put<MovieAdd>(`${this.apiUrl}/${movie.id}`, movie);
}

deleteMovie(id: string): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${id}`);
}

getById(id: number): Observable<any> {
  return this.http.get(`${this.apiUrl}/${id}`);
}
}
