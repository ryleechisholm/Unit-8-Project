package com.example.theater.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        System.out.println("getting employee");
        return employeeRepository.findEmployeeById(employeeId);
    }

    public void addNewEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByName(employee.getName());
        if (employeeOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) {
            throw new IllegalStateException("employee with id " + employeeId + " does not exist");
        }
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void updateEmployee(Long employeeId, String name) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalStateException("Employee with id " + employeeId + " does not exist"));
        if (name != null && name.length() > 0 && !Objects.equals(employee.getName(), name)) {
            employee.setName(name);
        }
    }
}
