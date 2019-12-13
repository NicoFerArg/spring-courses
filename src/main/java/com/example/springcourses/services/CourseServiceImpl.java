package com.example.springcourses.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.springcourses.exceptions.EntityNotFoundException;
import com.example.springcourses.models.Course;
import com.example.springcourses.repositories.CourseRepository;


@Service
public class CourseServiceImpl implements CourseService{
	
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
		return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course has not been founded"));
	}
	
	public String addCourse(Course course) {
		courseRepository.save(course);
		return "Course added succesfully";
	}
	
	public String updateCourseById(Integer id, Course course) throws EntityNotFoundException{
		if(!courseRepository.findById(id).isPresent()) {
			throw new EntityNotFoundException("Course has not been founded");
		}
		course.setId(id);
		courseRepository.save(course);
		return null;
	}
	
	public String deleteCourseById(Integer id) throws EntityNotFoundException{
		if(!courseRepository.findById(id).isPresent()) {
			throw new EntityNotFoundException("Course has not been founded");
		}
		courseRepository.deleteById(id);
		return "Course has been deleted";
	}

	
}
