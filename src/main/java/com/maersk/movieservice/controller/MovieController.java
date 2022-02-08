package com.maersk.movieservice.controller;

import com.maersk.movieservice.model.Movie;
import com.maersk.movieservice.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moviedb/v1")
public class MovieController {

    @Autowired
    MovieServiceImpl movieService;

    @PostMapping("/insert-movie")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.CREATED);
    }

    @PutMapping("/update-movie")
    public ResponseEntity<Movie> updateMovie(@RequestParam Integer id, @RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.updateMovie(id, movie), HttpStatus.OK);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/moviesByYear/{year}")
    public ResponseEntity<List<Movie>> getMoviesByYear(@PathVariable String year) {
        return new ResponseEntity<>(movieService.getMoviesByYear(year), HttpStatus.OK);
    }

    @GetMapping("/moviesByRating/{rating}")
    public ResponseEntity<List<Movie>> getMoviesByRating(@PathVariable String rating) {
        return new ResponseEntity<>(movieService.getMoviesByRating(rating), HttpStatus.OK);
    }
}
