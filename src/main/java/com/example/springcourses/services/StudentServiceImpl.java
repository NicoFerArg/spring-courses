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

	private final static Logger LOGGER = Logger.getLogger("com.examples.courses.services.StudentService");

	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudentPaginatorSort(Pageable paging) {
		
		Page<Student> pagedResult = studentRepository.findAll(paging);
		
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<Student>();
		}
		
	}
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public Student getStudentById(Integer id){
		return studentRepository.findById(id).orElseThrow(() -> {
			LOGGER.log(Level.WARNING,"Student has not been found");
			return new EntityNotFoundException("Student has not been found");
		});
	}
	
	public String addStudent(Student student) {
		studentRepository.save(student);
		LOGGER.log(Level.INFO,"Student added succesfully. The new student is "+student.getName());
		return "Student added succesfully";
	}
	
	public String updateStudentById(Integer id, Student student) throws EntityNotFoundException{
		if(!studentRepository.findById(id).isPresent()) {
			LOGGER.log(Level.WARNING,"Student has not been found");
			throw new EntityNotFoundException("Student has not been found");
		}
		student.setId(id);
		LOGGER.log(Level.INFO,"Student updated succesfully. The new student is "+student.getName());
		studentRepository.save(student);
		return "Student updated succesfully";
	}
	
	public String deleteStudentById(Integer id) throws EntityNotFoundException{
		if(!studentRepository.findById(id).isPresent()) {
			LOGGER.log(Level.WARNING,"Student has not been found");
			throw new EntityNotFoundException("Student has not been found");
		}
		studentRepository.deleteById(id);
		LOGGER.log(Level.INFO,"Student deleted succesfully.");
		return "Course has been deleted";
	}
	
}
