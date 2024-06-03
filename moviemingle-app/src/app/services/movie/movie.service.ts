import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MovieDto } from 'src/app/models/movies/MovieDto';
import { PageReq } from 'src/app/models/pages/PageReq';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private readonly apiUrl = 'movies';

constructor(private http: HttpClient) { }

getAllMovies(page: PageReq | null, filterParams?: any): Observable<any> {
  let params = new HttpParams();
  if (page) {
    params = params.append('page', page.page.toString());
    params = params.append('size', page.size.toString());
  }
  if (filterParams) {
    Object.keys(filterParams).forEach(key => {
      if (filterParams[key] !== null && filterParams[key] !== undefined && filterParams[key] !== '') {
        params = params.append(key, filterParams[key]);
      }
    });
  }

  return this.http.get(this.apiUrl, { params });
}

getMovie(id: number): Observable<MovieDto> {
  return this.http.get<MovieDto>(`${this.apiUrl}/${id}`);
}

addMovie(movie: MovieDto): Observable<MovieDto> {
  return this.http.post<MovieDto>(`${this.apiUrl}/omdb`, movie);
}

updateMovie(movie: MovieDto): Observable<MovieDto> {
  return this.http.put<MovieDto>(`${this.apiUrl}/update/${movie.id}`, movie);
}

deleteMovie(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${id}`);
}

getById(id: number): Observable<any> {
  return this.http.get(`${this.apiUrl}/${id}`);
}
}
