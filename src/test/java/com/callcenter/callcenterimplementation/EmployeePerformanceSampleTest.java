package com.callcenter.callcenterimplementation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmployeePerformanceSampleTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void validate_invalid_employee_sample() {

        exception.expect(InvalidRequestException.class);
        EmployeePerformanceSample employeePerformanceSample = new EmployeePerformanceSample(null,
                null,null, 0);
    }
    @Test
    public void validate_invalid_employee_sample_1() {

        exception.expect(InvalidRequestException.class);
        EmployeePerformanceSample employeePerformanceSample = new EmployeePerformanceSample(new int[][]{},
                new int[][]{},new int[]{}, 0);
    }

    @Test
    public void validate_correct_person_chosen_for_attending_next_call() {
        EmployeePerformanceSample employeePerformanceSample = new EmployeePerformanceSample(new int[][]{{1,2},{2,3}},
                new int[][]{{4},{2}},new int[]{1,2,3}, 1);
        employeePerformanceSample.doBasicValidation();
        int je_index = employeePerformanceSample.findIndexOfBestExecutiveToTakeCall
                (new ArrayList<Employee>(employeePerformanceSample.getJunior_Executives()));
        int se_index = employeePerformanceSample.findIndexOfBestExecutiveToTakeCall
                (new ArrayList<Employee>(employeePerformanceSample.getSenior_Executives()));
        assertTrue(je_index == 0);
        assertTrue(se_index == 1);
    }

    @Test
    public void validate_next_person_to_attend_call_not_found() {
        EmployeePerformanceSample employeePerformanceSample = new EmployeePerformanceSample(new int[][]{{1,2},{2,3}},
                new int[][]{{1}},new int[]{1,2,3}, 1);
        int se_index = employeePerformanceSample.findIndexOfBestExecutiveToTakeCall
                (new ArrayList<Employee>(employeePerformanceSample.getSenior_Executives()));
        int je_index = employeePerformanceSample.findIndexOfBestExecutiveToTakeCall
                (new ArrayList<Employee>(employeePerformanceSample.getJunior_Executives()));
        //The calls which can be taken by each employee is set to zero, hence none takes the call
        assertTrue(je_index == -1);
        assertTrue(se_index == -1);
    }
}