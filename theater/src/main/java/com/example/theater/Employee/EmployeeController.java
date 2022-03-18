package com.example.theater.Employee;

import com.example.theater.Movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "theater/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{employeeId}")
    public Employee getEmployeeById(
            @PathVariable("employeeId") Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public void createEmployee(@RequestBody Employee employee) {
        employeeService.addNewEmployee(employee);
    }

    @DeleteMapping(path = "{employeeId}")
    public void deleteEmployee(
            @PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping(path = "{employeeId}")
    public void udpateEmployee(
            @PathVariable("employeeId") Long employeeId,
            @RequestParam(required = false) String name) {
        employeeService.updateEmployee(employeeId, name);
    }
}
