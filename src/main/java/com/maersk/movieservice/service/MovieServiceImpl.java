package com.maersk.movieservice.service;

import com.maersk.movieservice.model.Movie;
import com.maersk.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Integer id, Movie movie) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        optionalMovie.ifPresent(m -> {
            m.setName(movie.getName());
            m.setYear(movie.getYear());
            m.setRating(movie.getRating());
        });
        return movieRepository.save(optionalMovie.get());
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByYear(String year) {
        return movieRepository.findMoviesByYear(year);
    }

    @Override
    public List<Movie> getMoviesByRating(String rating) {
        return movieRepository.findMoviesByRating(rating);
    }
}
