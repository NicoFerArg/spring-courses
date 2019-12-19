package com.example.springcourses;

import com.example.springcourses.validators.ValidateRut;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RutValidatorTest {

    @Test
    public void isValid(){
        assertTrue(ValidateRut.validateRut("12-4"));
    }

    @Test
    public void isInvalid(){
        assertFalse(ValidateRut.validateRut("12-2"));
    }

    @Test
    public void isValidDots(){
        assertTrue(ValidateRut.validateRut("17.996.520-8"));
    }

    @Test
    public void isInvalidDots(){
        assertFalse(ValidateRut.validateRut("17.996.520-3"));
    }

    @Test
    public void isValidK(){
        assertTrue(ValidateRut.validateRut("6-k"));
    }

    @Test
    public void isInvalidK(){
        assertFalse(ValidateRut.validateRut("12-K"));
    }

}
