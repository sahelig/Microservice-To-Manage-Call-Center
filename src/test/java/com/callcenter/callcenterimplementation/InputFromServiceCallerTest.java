package com.callcenter.callcenterimplementation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class InputFromServiceCallerTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void test_parse_Input_To_Create_Call_Center_1()
    {
        InputFromServiceCaller inputFromServiceCaller = new InputFromServiceCaller();
        inputFromServiceCaller.setJe(new String[]{"1","2"});
        inputFromServiceCaller.setSe(new String[]{"1"});
        inputFromServiceCaller.setMgr("1,2,3");
        inputFromServiceCaller.setNumber_of_calls(10);

        CallCenterImplementation callCenterImplementation = inputFromServiceCaller.parseInputAndCreateCallCenterImpl();

        assertTrue(Integer.parseInt(callCenterImplementation.getNumber_of_calls()) == 10);
        assertTrue(Integer.parseInt(callCenterImplementation.getResolved()) == 2);
        assertTrue(Integer.parseInt(callCenterImplementation.getUnresolved()) == 8);
        assertTrue(Integer.parseInt(callCenterImplementation.getTotalTimeTakenInMinutes()) == 3);
    }

    @Test
    public void test_parse_Input_To_Create_Call_Center_2()
    {
        InputFromServiceCaller inputFromServiceCaller = new InputFromServiceCaller();
        inputFromServiceCaller.setJe(new String[]{"10","10"});
        inputFromServiceCaller.setSe(new String[]{"16"});
        inputFromServiceCaller.setMgr("1");
        inputFromServiceCaller.setNumber_of_calls(10);

        CallCenterImplementation callCenterImplementation = inputFromServiceCaller.parseInputAndCreateCallCenterImpl();

        assertTrue(Integer.parseInt(callCenterImplementation.getNumber_of_calls()) == 10);
        assertTrue(Integer.parseInt(callCenterImplementation.getResolved()) == 1);
        assertTrue(Integer.parseInt(callCenterImplementation.getUnresolved()) == 9);
        assertTrue(Integer.parseInt(callCenterImplementation.getTotalTimeTakenInMinutes()) == 37);
    }

    @Test
    public void test_parse_Input_To_Create_Call_Center_no_calls_resolved()
    {
        InputFromServiceCaller inputFromServiceCaller = new InputFromServiceCaller();
        inputFromServiceCaller.setJe(new String[]{"10","10"});
        inputFromServiceCaller.setSe(new String[]{"16"});
        inputFromServiceCaller.setMgr("30");
        inputFromServiceCaller.setNumber_of_calls(10);

        CallCenterImplementation callCenterImplementation = inputFromServiceCaller.parseInputAndCreateCallCenterImpl();

        assertTrue(Integer.parseInt(callCenterImplementation.getNumber_of_calls()) == 10);
        assertTrue(Integer.parseInt(callCenterImplementation.getResolved()) == 0);
        assertTrue(Integer.parseInt(callCenterImplementation.getUnresolved()) == 10);
        assertTrue(Integer.parseInt(callCenterImplementation.getTotalTimeTakenInMinutes()) == 66);
    }
    @Test
    public void test_parse_Input_To_Create_Call_Center_invalid_input_expect_exception()
    {
        InputFromServiceCaller inputFromServiceCaller = new InputFromServiceCaller();
        inputFromServiceCaller.setJe(new String[]{"10","10"});
        inputFromServiceCaller.setSe(new String[]{"16"});
        inputFromServiceCaller.setNumber_of_calls(10);

        exception.expect(InvalidRequestException.class);
        CallCenterImplementation callCenterImplementation = inputFromServiceCaller.parseInputAndCreateCallCenterImpl();
    }
}