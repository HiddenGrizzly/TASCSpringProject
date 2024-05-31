export interface MovieDto {
  id?: number;

  movieTitle: string;

  year: string;

  imdbId: string;

  rated: string;

  released: string;

  runtime: string;

  plot: string;

  awards: string;

  poster: string;

  trailer: string;

  price: number;

  writers: Set<string>;

  actors: Set<string>;

  directors: Set<string>;

  genres: Set<string>;
}
