package com.maersk.movieservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.movieservice.model.Movie;
import com.maersk.movieservice.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    public void testCreateMovie() throws Exception {
        String url = "/moviedb/v1/insert-movie";

        mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .content(asJsonString(new Movie(1,"Matrix","2000","4")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    public void testUpdateMovie() throws Exception {
        String url = "/moviedb/v1/update-movie";
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");

        mockMvc.perform(MockMvcRequestBuilders
                .put(url).params(requestParams)
                .content(asJsonString(new Movie(1,"Matrix","2001","4")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testGetAllMovies() throws Exception {
        String url = "/moviedb/v1/movies";

        mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testGetMoviesByYear() throws Exception {
        String url = "/moviedb/v1/moviesByYear/{year}";

        mockMvc.perform(MockMvcRequestBuilders
                .get(url,"2000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testGetMoviesByRating() throws Exception {
        String url = "/moviedb/v1/moviesByRating/{rating}";

        mockMvc.perform(MockMvcRequestBuilders
                .get(url,"5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
