package com.example.springcourses.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcourses.models.Course;
import com.example.springcourses.services.CourseService;

@RestController
@RequestMapping("/courses")
public class  CourseController {

	private CourseService courseService;
	
	CourseController(CourseService courseService){
		this.courseService = courseService;
	}
	
	@GetMapping
	public List<Course> getCoursePaginatorSort(Pageable paging) {
		return courseService.getCoursePaginatorSort(paging);
	}
	
	@GetMapping("/all")
	public Iterable<Course> getAllCourses() {
		return courseService.getAllCourses();
	}
	
	@GetMapping("/{id}")
	public Course getCourseById(@PathVariable Integer id) {
		return courseService.getCourseById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String addCourse(@Valid @RequestBody Course course) {
		courseService.addCourse(course);
		return "Course added. The new course is "+course.getName();
	}
	
	@PutMapping("/{id}")
	public String updateCourseById(@PathVariable Integer id, @RequestBody Course course) {
		return courseService.updateCourseById(id, course);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteCourseById(@PathVariable Integer id, HttpServletResponse response) {
		courseService.deleteCourseById(id);
		return "Course deleted with id "+id;
	}
	
	
	
}
