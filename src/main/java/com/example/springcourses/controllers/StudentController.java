package com.example.springcourses.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcourses.models.Student;
import com.example.springcourses.services.StudentService;

@Validated
@RestController
@RequestMapping("/students")
public class StudentController {

	
	private StudentService studentService;
	
	StudentController(StudentService studentService){
		this.studentService = studentService;
	}
	
	@GetMapping
	public List<Student> getStudentPaginatorSort(Pageable paging) {
		return studentService.getStudentPaginatorSort(paging);
	}
	
	@GetMapping("/all")
	public Iterable<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Integer id) {
		return studentService.getStudentById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String addStudent(@Valid @RequestBody Student student) {
		studentService.addStudent(student);
		return "Student added. The new student is "+student.getName();
	}

	@PutMapping("/{id}")
	public String updateStudentById(@PathVariable Integer id, @Valid @RequestBody Student student) {
		return studentService.updateStudentById(id, student);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteStudentById(@PathVariable Integer id) {
		studentService.deleteStudentById(id);
		return "Student deleted with id "+id;
	}
	
}
