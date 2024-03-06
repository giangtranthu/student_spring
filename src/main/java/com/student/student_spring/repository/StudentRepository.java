package com.student.student_spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.student_spring.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	 Optional<Student> findByEmail(String email);
}
