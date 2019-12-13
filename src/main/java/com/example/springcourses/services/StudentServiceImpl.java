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
		LOGGER.log(Level.INFO, "Entra al service");
		return studentRepository.findAll();
	}
	
	public Student getStudentById(Integer id){
		return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student has not been founded"));
	}
	
	public String addStudent(Student student) {
		studentRepository.save(student);
		return "Course added succesfully";
	}
	
	public String updateStudentById(Integer id, Student student) throws EntityNotFoundException{
		if(!studentRepository.findById(id).isPresent()) {
			throw new EntityNotFoundException("Student has not been founded");
		}
		student.setId(id);
		studentRepository.save(student);
		return null;
	}
	
	public String deleteStudentById(Integer id) throws EntityNotFoundException{
		if(!studentRepository.findById(id).isPresent()) {
			throw new EntityNotFoundException("Student has not been founded");
		}
		studentRepository.deleteById(id);
		return "Course has been deleted";
	}
	
}
