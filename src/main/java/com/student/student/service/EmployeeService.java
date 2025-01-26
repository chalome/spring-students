package com.student.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.student.student.models.Employee;
import com.student.student.repository.EmployeeRepository;

@Service
public class EmployeeService {
private final EmployeeRepository employeeRepository;
public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository=employeeRepository;
}
public List<Employee> getAllEmployees(){
    return employeeRepository.findAll();
}
public Optional<Employee> getEmployeeById(int id){
    return employeeRepository.findById(id);
}
public Employee addEmployee(Employee employee){
    return employeeRepository.save(employee);
}
public Employee UpdateEmployee(Employee employee){
    Employee existingEmployee=employeeRepository.findById(employee.getId()).orElse(null);
    existingEmployee.setFirstName(employee.getFirstName());
    existingEmployee.setLastName(employee.getLastName());
    existingEmployee.setEmail(employee.getEmail());
    existingEmployee.setPhone(employee.getPhone());
    existingEmployee.setSalary(employee.getSalary());
    existingEmployee.setService(employee.getService());
    return employeeRepository.save(existingEmployee);
}
public String deleteEmployee(int id){
    employeeRepository.deleteById(id);
    return "Employee removed !! " + id;
}

}
