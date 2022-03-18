package com.example.theater.Ticket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TicketConfig {

    @Bean
    CommandLineRunner commandLineRunner3(TicketRepository repository) {
        return args -> {
            Ticket one = new Ticket();
            Ticket two = new Ticket();
            Ticket three = new Ticket();
            Ticket four = new Ticket();
            Ticket five = new Ticket();
            Ticket six = new Ticket();
            Ticket seven = new Ticket();
            Ticket eight = new Ticket();
            Ticket nine = new Ticket();
            repository.saveAll(List.of(one, two, three, four, five, six, seven, eight, nine));
        };
    }
}
