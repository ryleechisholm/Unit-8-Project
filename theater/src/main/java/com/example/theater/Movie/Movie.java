package com.example.theater.Movie;


import com.example.theater.Employee.Employee;
import com.example.theater.Ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToMany
    @JoinTable(
            name="employee_assigned",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )

    private Set<Employee> assignedEmployees = new HashSet<>();

    private String name;

    public Movie() {
    }

    public Movie(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(Set<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
