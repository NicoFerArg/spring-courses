package com.example.springcourses.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.springcourses.exceptions.EntityNotFoundException;
import com.example.springcourses.models.Course;
import com.example.springcourses.repositories.CourseRepository;


@Service
public class CourseServiceImpl implements CourseService{

	private static final String COURSE_NOT_FOUND = "Course has not been found";

	private static final Logger LOGGER = Logger.getLogger("com.examples.courses.services.CourseService");

	private CourseRepository courseRepository;
	
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public List<Course> getCoursePaginatorSort(Pageable paging) {
		
		Page<Course> pagedResult = courseRepository.findAll(paging);
		
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<>();
		}
		
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
	
	public Course getCourseById(Integer id){
		return courseRepository.findById(id).orElseThrow(() -> {
			LOGGER.log(Level.WARNING,COURSE_NOT_FOUND);
			return new EntityNotFoundException(COURSE_NOT_FOUND);
		});
	}
	
	public String addCourse(Course course) {
		courseRepository.save(course);
		LOGGER.log(Level.INFO,"Course added successfully. New course is "+course.getName());
		return "Course added successfully";
	}
	
	public String updateCourseById(Integer id, Course course){
		if(!courseRepository.findById(id).isPresent()) {
			LOGGER.log(Level.WARNING, COURSE_NOT_FOUND);
			throw new EntityNotFoundException(COURSE_NOT_FOUND);
		}
		course.setId(id);
		courseRepository.save(course);
		LOGGER.log(Level.INFO,"Course updated successfully. New course is "+course.getName());
		return "Course updated successfully";
	}
	
	public String deleteCourseById(Integer id){
		if(!courseRepository.findById(id).isPresent()) {
			LOGGER.log(Level.WARNING, COURSE_NOT_FOUND);
			throw new EntityNotFoundException(COURSE_NOT_FOUND);
		}
		courseRepository.deleteById(id);
		LOGGER.log(Level.INFO,"Course deleted successfully.");
		return "Course has been deleted";
	}
	
}
