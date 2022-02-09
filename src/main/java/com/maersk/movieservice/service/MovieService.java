package com.maersk.movieservice.service;

import com.maersk.movieservice.exception.MissingFieldValueException;
import com.maersk.movieservice.model.Movie;

import java.util.List;

public interface MovieService {

    public Movie createMovie(Movie movie);

    public Movie updateMovie(Integer id, Movie movie);

    public List<Movie> getAllMovies();

    public List<Movie> getMoviesByYear(String year);

    public List<Movie> getMoviesByRating(String rating);
}
