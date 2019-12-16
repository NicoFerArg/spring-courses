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

	private final static Logger LOGGER = Logger.getLogger("com.examples.courses.services.CourseService");

	private CourseRepository courseRepository;
	
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public List<Course> getCoursePaginatorSort(Pageable paging) {
		
		Page<Course> pagedResult = courseRepository.findAll(paging);
		
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<Course>();
		}
		
	}
	
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
	
	public Course getCourseById(Integer id){
		return courseRepository.findById(id).orElseThrow(() -> {
			LOGGER.log(Level.WARNING,"Course has not been found");
			return new EntityNotFoundException("Course has not been found");
		});
	}
	
	public String addCourse(Course course) {
		courseRepository.save(course);
		LOGGER.log(Level.INFO,"Course added succesfully. New course is "+course.getName());
		return "Course added succesfully";
	}
	
	public String updateCourseById(Integer id, Course course) throws EntityNotFoundException{
		if(!courseRepository.findById(id).isPresent()) {
			LOGGER.log(Level.WARNING, "Course has not been found");
			throw new EntityNotFoundException("Course has not been found");
		}
		course.setId(id);
		courseRepository.save(course);
		LOGGER.log(Level.INFO,"Course updated succesfully. New course is "+course.getName());
		return "Course updated succesfully";
	}
	
	public String deleteCourseById(Integer id) throws EntityNotFoundException{
		if(!courseRepository.findById(id).isPresent()) {
			LOGGER.log(Level.WARNING, "Course has not been found");
			throw new EntityNotFoundException("Course has not been found");
		}
		courseRepository.deleteById(id);
		LOGGER.log(Level.INFO,"Course deleted succesfully.");
		return "Course has been deleted";
	}
	
}
