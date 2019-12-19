package com.example.springcourses.repositories;

import com.example.springcourses.models.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

}
