package com.student.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.student.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    
}
