package com.example.theater.Movie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MovieConfig {

    @Bean
    CommandLineRunner commandLineRunner2(MovieRepository repository) {
        return args -> {
            Movie movie1 = new Movie("The Conjuring");
            Movie movie2 = new Movie("Daddy Daycare");
            repository.saveAll(List.of(movie1, movie2));
        };
    }
}
