package com.student.student_spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.student_spring.service.IStudentService;
import com.student.student_spring.model.Student;

import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
	private final IStudentService studentService;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	public ResponseEntity<List<Student>> getStudents() {
		return new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	public Student addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	public Student updateStudent(@RequestBody Student student, @PathVariable Long id) {
		return studentService.updateStudent(student, id);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	public void deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
	}

	@GetMapping("/student/{id}")
	@PreAuthorize("hasRole('ROLE_TEACHER') or (hasRole('ROLE_STUDENT') and #email == principal.username)")
	public Student getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}

}
