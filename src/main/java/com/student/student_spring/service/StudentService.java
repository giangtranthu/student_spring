package com.student.student_spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.student_spring.exception.StudentAlreadyExistException;
import com.student.student_spring.exception.StudentNotFoundException;
import com.student.student_spring.model.Student;
import com.student.student_spring.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{
	
	
	private final StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	private boolean studentAlreadyExist(String email) {
		return studentRepository.findByEmail(email).isPresent();
	}

	@Override
	public Student addStudent(Student student) {
		if (studentAlreadyExist(student.getEmail())) {
			throw new StudentAlreadyExistException(student.getEmail() + " already exist");
		}
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found")  );
	}

	@Override
	public void deleteStudent(Long id) {
		if (!studentRepository.existsById(id)) {
			throw new StudentNotFoundException("Student not found");
		}
		studentRepository.deleteById(id);
	}

}
