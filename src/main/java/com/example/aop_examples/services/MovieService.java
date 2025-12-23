package com.example.aop_examples.services;

import com.example.aop_examples.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {

    private final ArrayList<Movie> movies =
            new ArrayList<>(
                    Arrays.asList(
                            new Movie("The Game", 1997),
                            new Movie("The Ninth Gate", 1999),
                            new Movie("The Devil's Advocate", 1997)
                    )
            );

    public Movie getMovieByTitle(String title) {
        System.out.println("getMovieByTitle called");
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }

    public List<Movie> getAllMovies() {
        System.out.println("getAllMovies called");
        return movies;
    }

    public Movie createMovie(String title, int year) {
        System.out.println("createMovie called");
        Movie created = new Movie(title, year);
        movies.add(created);
        return created;
    }
}
