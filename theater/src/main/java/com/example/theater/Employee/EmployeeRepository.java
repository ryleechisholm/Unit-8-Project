package com.example.theater.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e Where e.name = ?1")
    Optional<Employee> findEmployeeByName(String name);

    @Query("select e from Employee e where e.id = ?1")
    Employee findEmployeeById(Long id);
}
