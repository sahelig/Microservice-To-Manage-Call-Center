package com.callcenter.callcenterimplementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void test_employee_should_not_recieve_call()
    {
        Employee e = new JuniorExecutive("je1", new int[]{10,20,20});
        e.callsAttended = 3;
        assert e.canTakeTheCall()==false;
    }
    @Test
    public void test_employee_should_not_receive_call_as_max_calls_is_0()
    {
        Employee e = new JuniorExecutive("je1", new int[]{10,20,20});
        assert e.canTakeTheCall()==false;
    }
    @Test
    public void test_employee_should_receive_call_as_max_calls_is_5()
    {
        Employee e = new SeniorExecutive("je1", new int[]{10,20,20});
        e.setMaxCallsPossible(5);
        assert e.canTakeTheCall();
    }
    @Test
    public void test_employee_time_for_next_call()
    {
        Employee e = new SeniorExecutive("je1", new int[]{10,20,20});
        e.setMaxCallsPossible(5);
        e.callsAttended = 1;
        assert e.timeTakenForNextCall()==20;
    }
    @Test
    public void test_employee_does_not_escalate_call()
    {
        Employee e = new JuniorExecutive("je1",new int[]{6,4,3});
        e.setMaxCallsPossible(2);
        e.callsAttended = 0;
        assert e.takeCallAndEscalateIfRequired()==false;
        assert e.timeTakenInMinutes==6;
        assert e.callsAttended==1;
        assert e.resolved==1;
    }
    @Test
    public void test_employee_does_escalates_call()
    {
        Employee e = new JuniorExecutive("je1", new int[]{10,4,3});
        e.setMaxCallsPossible(2);
        e.callsAttended = 0;
        assert e.takeCallAndEscalateIfRequired()==true;
        assert e.timeTakenInMinutes==10;
        assert e.callsAttended==1;
        assert e.resolved==0;
    }
}