package com.example.theater.Employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            Employee mike = new Employee("Mike");
            Employee lauren = new Employee("Lauren");
            repository.saveAll(List.of(mike, lauren));
        };
    }
}
