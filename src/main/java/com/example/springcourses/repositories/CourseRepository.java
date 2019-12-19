package com.example.springcourses.repositories;

import com.example.springcourses.models.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {

}
