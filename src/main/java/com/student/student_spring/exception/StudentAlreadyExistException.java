package com.student.student_spring.exception;

public class StudentAlreadyExistException extends RuntimeException{
	public StudentAlreadyExistException(String message){
		super(message);
	}
}
