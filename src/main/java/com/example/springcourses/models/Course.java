package com.example.springcourses.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="COURSE")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="course_seq")
	@SequenceGenerator(name = "course_seq", sequenceName = "course_seq", initialValue = 1, allocationSize=1)
	@Column(name="ID")
	private Integer id;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="course")
	private List<Student> students;
	
	@Column(name="NAME")
	@NotEmpty
	@Size(max=255)
	private String name;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
