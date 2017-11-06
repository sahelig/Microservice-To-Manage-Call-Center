package com.callcenter.callcenterimplementation;

import java.util.ArrayList;
import java.util.List;

public class CallCenterImplementation {

    //Needs to be set after creating the call center
    private int number_of_calls;

    //Calculated fields
    private int resolved = 0;
    private int unresolved = 0;
    private int totalTimeTakenInMinutes = 0;

    private List<EmployeePerformanceSample> performance = new ArrayList<>();

    public List<EmployeePerformanceSample> getPerformance() {
        return performance;
    }

    public void addPerformanceSample(EmployeePerformanceSample performanceSample) {
        this.performance.add(performanceSample);
    }

    public void setNumber_of_calls(int number_of_calls) {
        this.number_of_calls = number_of_calls;
    }

    private void setResolved(EmployeePerformanceSample performanceSample) {
        resolved = resolved + performanceSample.getCallsResolvedByThisSampleSet();
    }

    private void setUnresolved(EmployeePerformanceSample performanceSample) {
        unresolved = (number_of_calls - performanceSample.getCallsResolvedByThisSampleSet());
    }

    private void setTotalTimeTakenInMinutes(EmployeePerformanceSample performanceSample) {
        totalTimeTakenInMinutes = totalTimeTakenInMinutes + performanceSample.getTotalTimeTakenInMinutesByThisSampleSet();
    }

    //Getters for the JSON
    public String getNumber_of_calls() {
        return Integer.toString(number_of_calls);
    }

    public String getResolved() {
        return Integer.toString(resolved);
    }

    public String getUnresolved() {
        return Integer.toString(unresolved);
    }

    public String getTotalTimeTakenInMinutes() {
        return Integer.toString(totalTimeTakenInMinutes);
    }

    //Does validation of DS and calculates the times required
    public void doCallCenterPerformanceMetricCalculation() throws InvalidRequestException {

        for(EmployeePerformanceSample performanceSample : performance) {
            if (performanceSample == null) {
                throw new InvalidRequestException("The performanceSample input of all employees of call center is not present! aborting!");
            }

            performanceSample.doBasicValidation();

            performanceSample.recieveCallsBasedOnPerformance();

            setTotalTimeTakenInMinutes(performanceSample);
            setResolved(performanceSample);
            setUnresolved(performanceSample);
        }
    }
}
