package com.example.moviemingle.controllers.movies;

import com.example.moviemingle.dtos.movies.MovieCreateDTO;
import com.example.moviemingle.dtos.movies.MovieDTO;
import com.example.moviemingle.dtos.movies.MovieOmdbDTO;
import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.models.pages.PageRes;
import com.example.moviemingle.services.apis.ApiService;
import com.example.moviemingle.services.movies.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Movie", description = "Movie controller")
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ApiService apiService;


    @Operation(summary = "Lấy về danh sách sản phẩm có phân trang.", description = "Trả về danh sách sản phẩm theo trang.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ"),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ")
    })
    @GetMapping
    public ResponseEntity<PageRes<MovieDTO>> showMovieList(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "actor", required = false) String actor,
            @RequestParam(value = "director", required = false) String director,
            @RequestParam(value = "writer", required = false) String writer,
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
        Page<MovieDTO> moviePage = movieService.findAllMovies(pageable, title, actor, director, writer, genre, minPrice, maxPrice);
        return ResponseEntity.ok(new PageRes<>(moviePage));
    }

    @Operation(summary = "Lấy về một phim theo Id.", description = "Trả về một phim dựa theo Id truyền vào.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy"),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> showMovieDetail(@PathVariable Long id) {
        MovieDTO movieDTO = movieService.findMovieById(id);
        if (movieDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDTO);
    }

    @Operation(summary = "Thêm một bộ phim mới.", description = "Trả về phim đã được thêm.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tạo thành công"),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ"),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ")
    })
    @PostMapping("/")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO) {
        Movie addedMovie = movieService.createMovie(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMovie);
    }

    @PostMapping("/omdb")
    public ResponseEntity<Movie> addMovieFromOmdb(@RequestBody @Valid MovieCreateDTO movieCreateDTO) {
        MovieOmdbDTO movieOmdbDTO = apiService.getMovieApi(movieCreateDTO.getTitle());
        Movie movie = movieService.creatMovieFromOmdb(movieOmdbDTO, movieCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    // Phương thức sửa thông tin của một bộ phim dựa trên ID
    @Operation(summary = "Sửa thông tin một phim theo Id.", description = "Trả về phim sau khi sửa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy"),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ")
    })
    @PutMapping("update/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody @Valid MovieDTO movieDTO) {
        movieService.updateMovie(id, movieDTO);
        return ResponseEntity.ok(movieService.updateMovie(id, movieDTO));
    }

    // Phương thức xóa một bộ phim dựa trên ID
    @Operation(summary = "Xóa một phim theo Id.", description = "Trả về phim đã xóa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy"),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("lists")
    public List<MovieDTO> getMoviesByIds(@RequestParam List<Long> ids){
        return movieService.getMoviesByIds(ids);
    }
}
