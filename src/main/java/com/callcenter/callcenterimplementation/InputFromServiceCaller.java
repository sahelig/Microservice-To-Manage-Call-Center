package com.callcenter.callcenterimplementation;

import java.util.Arrays;

public class InputFromServiceCaller {
    private Integer number_of_calls;
    private int je[][];
    private int se[][];
    private int mgr[];


    public void setJe(String[] je) {
        int length = je.length;
        this.je = new int[length][];
        for(int i=0;i<length;i++) {
            this.je[i] = Arrays.stream(je[i].split(",")).mapToInt(Integer::parseInt).toArray();;
        }
    }

    public void setSe(String[] se) {
        int length = se.length;
        this.se = new int[length][];
        for(int i=0;i<length;i++) {
            this.se[i] = Arrays.stream(se[i].split(",")).mapToInt(Integer::parseInt).toArray();;
        }
    }

    public void setMgr(String mgr) {
        String arr[] = mgr.split(",");
        this.mgr = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
    }
    public void setNumber_of_calls(Integer number_of_calls) {
        this.number_of_calls = number_of_calls;
    }

    public CallCenterImplementation parseInputAndCreateCallCenterImpl() throws InvalidRequestException {

        if(je==null || se == null || mgr==null || je.length == 0 || se.length == 0 || mgr.length==0)
            throw new InvalidRequestException("Somehow, all inputs aren't set!");

        EmployeePerformanceSample performace = new EmployeePerformanceSample(je,se,mgr,number_of_calls);

        CallCenterImplementation callCenterImplementation = new CallCenterImplementation();
        callCenterImplementation.addPerformanceSample(performace);
        callCenterImplementation.setNumber_of_calls(number_of_calls);
        callCenterImplementation.doCallCenterPerformanceMetricCalculation();

        return callCenterImplementation;
    }
}
