package com.example.theater.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m from Movie m Where m.name = ?1")
    Optional<Movie> findMovieByName(String name);

    @Query("select m from Movie m Where m.id = ?1")
    Movie findMovieById(Long id);
}
