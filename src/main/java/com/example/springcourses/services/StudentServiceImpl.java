package com.example.springcourses.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springcourses.exceptions.EntityNotFoundException;
import com.example.springcourses.models.Student;
import com.example.springcourses.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	private static final String STUDENT_NOT_FOUND = "Student has not been found";

	private static final Logger LOGGER = Logger.getLogger("com.examples.springcourses.services.StudentService");

	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudentPaginatorSort(Pageable paging) {
		
		Page<Student> pagedResult = studentRepository.findAll(paging);
		
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<>();
		}
		
	}
	
	public Iterable<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public Student getStudentById(Integer id){
		return studentRepository.findById(id).orElseThrow(() -> {
			LOGGER.log(Level.WARNING,STUDENT_NOT_FOUND);
			return new EntityNotFoundException(STUDENT_NOT_FOUND);
		});
	}
	
	public String addStudent(Student student) {
		studentRepository.save(student);
		LOGGER.log(Level.INFO,"Student added successfully. The new student is "+student.getName());
		return "Student added successfully";
	}
	
	public String updateStudentById(Integer id, Student student){
		if(!studentRepository.findById(id).isPresent()) {
			LOGGER.log(Level.WARNING,STUDENT_NOT_FOUND);
			throw new EntityNotFoundException(STUDENT_NOT_FOUND);
		}
		student.setId(id);
		LOGGER.log(Level.INFO,"Student updated successfully. The new student is "+student.getName());
		studentRepository.save(student);
		return "Student updated successfully";
	}
	
	public String deleteStudentById(Integer id){
		if(!studentRepository.findById(id).isPresent()) {
			LOGGER.log(Level.WARNING,STUDENT_NOT_FOUND);
			throw new EntityNotFoundException(STUDENT_NOT_FOUND);
		}
		studentRepository.deleteById(id);
		LOGGER.log(Level.INFO,"Student deleted successfully.");
		return "Course has been deleted";
	}
	
}
