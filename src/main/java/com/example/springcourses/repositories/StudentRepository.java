package com.example.springcourses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcourses.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
