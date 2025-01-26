package com.student.student.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.student.student.models.Employee;
import com.student.student.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee){
        return employeeService.getEmployeeById(id)
                .map(existingEmployee -> {
                    existingEmployee.setFirstName(updatedEmployee.getFirstName());
                    existingEmployee.setLastName(updatedEmployee.getLastName());
                    existingEmployee.setEmail(updatedEmployee.getEmail());
                    existingEmployee.setPhone(updatedEmployee.getPhone());
                    existingEmployee.setSalary(updatedEmployee.getSalary());
                    existingEmployee.setService(updatedEmployee.getService());
                    employeeService.addEmployee(existingEmployee);
                    return ResponseEntity.ok(existingEmployee);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable int id) {
        if (employeeService.getEmployeeById(id).isPresent()) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
