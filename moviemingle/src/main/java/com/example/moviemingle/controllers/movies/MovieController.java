package com.example.moviemingle.controllers.movies;

import com.example.moviemingle.dtos.movies.MovieInDTO;
import com.example.moviemingle.dtos.movies.MovieOutDTO;
import com.example.moviemingle.services.movies.MovieService;
import com.example.moviemingle.services.movies.MovieServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Operation(summary = "Lấy về danh sách sản phẩm có phân trang.", description = "Trả về danh sách sản phẩm theo trang.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ"),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ")
    })
    @GetMapping("")
    public ResponseEntity<Page<MovieOutDTO>> showMovieList(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "actor", required = false) String actor,
            @RequestParam(value = "director", required = false) String director,
            @RequestParam(value = "writer", required = false) String writer,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
        Page<MovieOutDTO> moviePage = movieService.findAllMovies(page, size, title, actor, director, writer, minPrice, maxPrice);
        return ResponseEntity.ok(moviePage);
    }

    @Operation(summary = "Lấy về một phim theo Id.", description = "Trả về một phim dựa theo Id truyền vào.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy"),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieOutDTO> showMovieDetail(@PathVariable Long id) {
        MovieOutDTO movieDTO = movieService.findMovieById(id);
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
    @PostMapping("/add")
    public ResponseEntity<MovieOutDTO> addMovie(@RequestBody MovieInDTO movieInDTO) {
        MovieOutDTO addedMovie = movieService.createMovie(movieInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMovie);
    }

    // Phương thức sửa thông tin của một bộ phim dựa trên ID
    @Operation(summary = "Sửa thông tin một phim theo Id.", description = "Trả về phim sau khi sửa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "400", description = "Yêu cầu không hợp lệ"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy"),
            @ApiResponse(responseCode = "500", description = "Lỗi máy chủ")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<MovieOutDTO> updateMovie(@PathVariable Long id, @RequestBody MovieInDTO movieInDTO) {
        MovieOutDTO updatedMovie = movieService.updateMovie(id, movieInDTO);
        if (updatedMovie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMovie);
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
}
