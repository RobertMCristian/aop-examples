package com.example.aop_examples.controller;

import com.example.aop_examples.model.Movie;
import com.example.aop_examples.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Validated
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        System.out.println("createMovie endpoint called");
        Movie saved = movieService.createMovie(movie.getTitle(), movie.getYear());
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable String title) {
        System.out.println("getMovieByTitle endpoint called");
        Movie movie = movieService.getMovieByTitle(title);
        return (movie != null) ? ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        System.out.println("getAllMovies endpoint called");
        return ResponseEntity.ok(movieService.getAllMovies());
    }
}
