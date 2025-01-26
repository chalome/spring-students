package com.student.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.student.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
