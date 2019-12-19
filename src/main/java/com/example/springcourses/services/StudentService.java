package com.example.springcourses.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.springcourses.models.Student;

public interface StudentService {

	List<Student> getStudentPaginatorSort(Pageable paging);
	Iterable<Student> getAllStudents();
	Student getStudentById(Integer id);
	String addStudent(Student student);
	String updateStudentById(Integer id, Student student);
	String deleteStudentById(Integer id);
	
}
