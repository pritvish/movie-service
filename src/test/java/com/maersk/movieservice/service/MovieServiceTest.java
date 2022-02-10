package com.maersk.movieservice.service;

import com.maersk.movieservice.exception.EmptyMovieDataBaseException;
import com.maersk.movieservice.exception.FutureDateException;
import com.maersk.movieservice.exception.IdNotFoundException;
import com.maersk.movieservice.exception.MissingFieldValueException;
import com.maersk.movieservice.model.Movie;
import com.maersk.movieservice.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService = new MovieServiceImpl();

    @Mock
    private MovieRepository movieRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMovie() {
        Mockito.when(movieRepository.save(Mockito.any())).thenReturn(getMovie());
        Movie mv = movieService.createMovie(getMovie());
        Assert.assertEquals("Matrix", mv.getName());

    }

    @Test(expected = MissingFieldValueException.class)
    public void testCreateMovieMissingFieldValueException() {
        Movie mv = movieService.createMovie(new Movie(1, "", "2000", "4"));
    }

    @Test
    public void testUpdateMovie() {
        Mockito.when(movieRepository.save(Mockito.any())).thenReturn(new Movie(1, "Matrix", "2000", "5"));
        Movie mv = movieService.createMovie(getMovie());
        Assert.assertEquals("5", mv.getRating());
    }

    @Test(expected = IdNotFoundException.class)
    public void testUpdateMovieIdNotFoundException() {
        Mockito.when(movieRepository.findById(1)).thenReturn(Optional.empty());
        movieService.updateMovie(1, getMovie());
    }

    @Test
    public void testGetAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(getMovie());
        movies.add(new Movie(2, "Matrix Reloaded", "2001", "5"));
        Mockito.when(movieRepository.findAll()).thenReturn(movies);
        List<Movie> moviesList = movieService.getAllMovies();
        Assert.assertEquals("Matrix", moviesList.get(0).getName());
        Assert.assertEquals("5", moviesList.get(1).getRating());
    }

    @Test(expected = EmptyMovieDataBaseException.class)
    public void testGetAllMoviesEmptyMovieDataBaseException() {
        List<Movie> movies = new ArrayList<>();
        Mockito.when(movieRepository.findAll()).thenReturn(movies);
        movieService.getAllMovies();
    }

    @Test
    public void testGetMoviesByYear() {
        Integer currentYear = LocalDateTime.now().getYear();
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Matrix Reloaded", currentYear.toString(), "5"));
        Mockito.when(movieRepository.findMoviesByYear(currentYear.toString())).thenReturn(movies);
        List<Movie> moviesList = movieService.getMoviesByYear(currentYear.toString());
        Assert.assertEquals("Matrix Reloaded", moviesList.get(0).getName());
    }

    @Test(expected = FutureDateException.class)
    public void testGetMoviesByYearFutureDateException() {
        Integer currentYear = LocalDateTime.now().getYear();
        Integer currentYearPlusOne = currentYear+1;
        List<Movie> movies = new ArrayList<>();
        movies.add(getMovie());
        movies.add(new Movie(2, "Matrix Reloaded", currentYear.toString(), "5"));
        List<Movie> moviesList = movieService.getMoviesByYear(currentYearPlusOne.toString());
    }

    @Test(expected = EmptyMovieDataBaseException.class)
    public void testGetMoviesByYearEmptyMovieDataBaseException() {
        List<Movie> movies = new ArrayList<>();
        Mockito.when(movieRepository.findMoviesByYear("2000")).thenReturn(movies);
        movieService.getMoviesByYear("2000");
    }

    @Test
    public void testGetMoviesByRating() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Matrix Reloaded", "2001", "5"));
        Mockito.when(movieRepository.findMoviesByRating("5")).thenReturn(movies);
        List<Movie> moviesList = movieService.getMoviesByRating("5");
        Assert.assertEquals("5", moviesList.get(0).getRating());
    }

    @Test(expected = EmptyMovieDataBaseException.class)
    public void testGetMoviesByRatingEmptyMovieDataBaseException() {
        List<Movie> movies = new ArrayList<>();
        Mockito.when(movieRepository.findMoviesByRating("5")).thenReturn(movies);
        movieService.getMoviesByRating("5");
    }

    private Movie getMovie() {
        return new Movie(1, "Matrix", "2000", "4");
    }
}
