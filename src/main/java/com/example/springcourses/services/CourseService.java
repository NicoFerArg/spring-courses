package com.example.springcourses.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.springcourses.models.Course;


public interface CourseService {
	
	public List<Course> getCoursePaginatorSort(Pageable paging);
	public Iterable<Course> getAllCourses();
	public Course getCourseById(Integer id);
	public String addCourse(Course course);
	public String updateCourseById(Integer id, Course course);
	public String deleteCourseById(Integer id);
	
}
