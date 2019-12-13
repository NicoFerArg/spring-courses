package com.example.springcourses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcourses.models.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
