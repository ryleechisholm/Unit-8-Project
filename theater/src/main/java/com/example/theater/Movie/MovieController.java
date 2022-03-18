package com.example.theater.Movie;

import com.example.theater.Employee.Employee;
import com.example.theater.Ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("theater/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping(path = "{movieId}")
    public Movie getMovieId(
            @PathVariable("movieId") Long movieId) {
        return movieService.getMovieById(movieId);
    }

    @PostMapping
    public void createMovie(@RequestBody Movie movie) throws Exception {
        movieService.addNewMovie(movie);
    }

    @DeleteMapping(path = "{movieId}")
    public void deleteMovie(
            @PathVariable("movieId") Long movieId) {
        movieService.deleteMovie(movieId);
    }

    @PutMapping(path = "{movieId}")
    public void updateMovie(
            @PathVariable("movieId") Long movieId,
            @RequestParam(required = false) Set<Employee> employees,
            @RequestParam(required = false) Set<Ticket> tickets,
            @RequestParam(required = false) String name) {
        movieService.updateMovie(movieId, employees, tickets, name);
    }
}
