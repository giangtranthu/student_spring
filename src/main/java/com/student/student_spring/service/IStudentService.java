package com.student.student_spring.service;

import java.util.List;

import com.student.student_spring.model.Student;

public interface IStudentService {
	Student addStudent(Student student);
	List<Student> getStudents();
	Student updateStudent(Student student, Long id);
	Student getStudentById(Long id);
	
	public void deleteStudent(Long id);
}
