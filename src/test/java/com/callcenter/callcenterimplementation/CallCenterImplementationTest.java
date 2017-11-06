package com.callcenter.callcenterimplementation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallCenterImplementationTest {

    EmployeePerformanceSample employeePerformanceSampleMock;


    CallCenterImplementation callCenterImplementation;

    @Before
    public void SetUp() {
        callCenterImplementation = new CallCenterImplementation();
        callCenterImplementation.setNumber_of_calls(10);
        employeePerformanceSampleMock = mock(EmployeePerformanceSample.class);
        callCenterImplementation.addPerformanceSample(employeePerformanceSampleMock);
        when(employeePerformanceSampleMock.getCallsResolvedByThisSampleSet()).thenReturn(10);
        when(employeePerformanceSampleMock.getTotalTimeTakenInMinutesByThisSampleSet()).thenReturn(100);
    }
    @Test
    public void test_that_metrics_of_call_center_are_correct(){
        callCenterImplementation.doCallCenterPerformanceMetricCalculation();
        assertEquals(callCenterImplementation.getNumber_of_calls(),"10");
        assertEquals(callCenterImplementation.getResolved(),"10");
        assertEquals(callCenterImplementation.getUnresolved(),"0");
        assertEquals(callCenterImplementation.getTotalTimeTakenInMinutes(),"100");

    }

}