package com.example.springcourses.controller;

import com.example.springcourses.controllers.StudentController;
import com.example.springcourses.models.Student;
import com.example.springcourses.repositories.StudentRepository;
import com.example.springcourses.services.StudentService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestStudentController {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    // write test cases here

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController,studentService).build();
    }

    @Test
    public void validateRutStudent()
            throws Exception {
        Student student = new Student();
        student.setRut("12-4");
        student.setAge(24);
        student.setName("Alejandro");
        Gson gson = new Gson();
        String json = gson.toJson(student);


        mockMvc.perform(MockMvcRequestBuilders
                .post("/students")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }


}
