package com.example.theater.Movie;

import com.example.theater.Employee.Employee;
import com.example.theater.Ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long movieId) {
        return movieRepository.findMovieById(movieId);
    }

    public Connection connectToDb(String dbname) throws Exception {
        Connection conn=null;
        try {
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname);
        } catch (Exception e) {
            throw new Exception("cannot connect to database?!");
        }
        return conn;
    }

    public void addNewMovie(Movie movie) throws Exception {
        Statement statement;
        ResultSet rs;
        Optional<Movie> movieOptional = movieRepository.findMovieByName(movie.getName());
        if (movieOptional.isPresent()) {
            throw new IllegalStateException("movie name taken");
        }
        movieRepository.save(movie);
        Connection conn = connectToDb("theater");
        int i = 0;
        while (i < 20) {
            statement = conn.createStatement();
            rs = statement.executeQuery("select max(id) from ticket");
            rs.next();
            int newId = (rs.getInt(1) + 1);
            String query = String.format("insert into ticket(id, movie_id) values('%s','%s');", newId, movie.getId());
            statement.executeUpdate(query);
            i+=1;
        }
    }

    public void deleteMovie(Long movieId) {
        boolean exists = movieRepository.existsById(movieId);
        if (!exists) {
            throw new IllegalStateException("movie with id " + movieId + " does not exist");
        }
        movieRepository.deleteById(movieId);
    }

    @Transactional
    public void updateMovie(Long movieId, Set<Employee> employees, Set<Ticket> tickets, String name) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalStateException("movie with id " + movieId + " does not exist"));
        if (name != null && name.length() > 0 && !Objects.equals(movie.getName(), name)) {
            movie.setName(name);
        }
    }
}
