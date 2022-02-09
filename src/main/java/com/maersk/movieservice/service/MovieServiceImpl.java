package com.maersk.movieservice.service;

import com.maersk.movieservice.exception.EmptyMovieDataBaseException;
import com.maersk.movieservice.exception.FutureDateException;
import com.maersk.movieservice.exception.IdNotFoundException;
import com.maersk.movieservice.exception.MissingFieldValueException;
import com.maersk.movieservice.model.Movie;
import com.maersk.movieservice.repository.MovieRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie createMovie(Movie movie) {
        if (StringUtils.isBlank(movie.getName()) || StringUtils.isBlank(movie.getYear()) || StringUtils.isBlank(movie.getRating())) {
            throw new MissingFieldValueException(HttpStatus.BAD_REQUEST, "Field cannot be empty");
        }
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Integer id, Movie movie) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isEmpty()) {
            throw new IdNotFoundException(HttpStatus.NOT_FOUND, "Supplied ID does not exist, enter valid ID");
        }
        optionalMovie.ifPresent(m -> {
            m.setName(movie.getName());
            m.setYear(movie.getYear());
            m.setRating(movie.getRating());
        });
        return movieRepository.save(optionalMovie.get());
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            throw new EmptyMovieDataBaseException(HttpStatus.NOT_FOUND, "No movies found in the Database");
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesByYear(String year) {

        Integer currentYear = LocalDateTime.now().getYear();
        if (currentYear.toString().compareTo(year) < 0) {
            throw new FutureDateException(HttpStatus.BAD_REQUEST, "Provide year value less than or equal to current year");
        }

        List<Movie> movies = movieRepository.findMoviesByYear(year);
        if (movies.isEmpty()) {
            throw new EmptyMovieDataBaseException(HttpStatus.NOT_FOUND, "No movies found in the Database for the year " + year);
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesByRating(String rating) {
        List<Movie> movies = movieRepository.findMoviesByRating(rating);
        if (movies.isEmpty()) {
            throw new EmptyMovieDataBaseException(HttpStatus.NOT_FOUND, "No movies found in the Database with " + rating + " ratings");
        }
        return movies;
    }
}
