package com.callcenter.callcenterimplementation;

import java.util.ArrayList;
import java.util.List;

public class EmployeePerformanceSample
{
    private Manager manager;
    private List<JuniorExecutive> junior_Executives = new ArrayList<>();
    private List<SeniorExecutive> senior_Executives = new ArrayList<>();

    private transient int number_of_calls = 0;
    private transient int callsAttended = 0;

    public EmployeePerformanceSample(int[][] je_mock_call_times, int[][] se_mock_call_times, int[] mgr_mock_call_times, Integer number_of_calls) {

        if((je_mock_call_times==null) || (se_mock_call_times==null) || (mgr_mock_call_times==null)
                || (je_mock_call_times.length == 0) || (se_mock_call_times.length == 0)
                || (mgr_mock_call_times.length == 0))
        {
            throw new InvalidRequestException("You have not provided input for je, se and manager, try again!!");
        }

        for(int i=0;i< je_mock_call_times.length;i++) {
            junior_Executives.add(new JuniorExecutive("je"+(i+1),je_mock_call_times[i]));
        }
        for(int i=0;i< se_mock_call_times.length;i++) {
            senior_Executives.add(new SeniorExecutive("se"+(i+1),se_mock_call_times[i]));
        }
        this.manager = new Manager("mgr", mgr_mock_call_times);
        this.number_of_calls = number_of_calls;
    }

    public Manager getManager() {
        return manager;
    }

    public List<JuniorExecutive> getJunior_Executives() {
        return junior_Executives;
    }

    public List<SeniorExecutive> getSenior_Executives() {
        return senior_Executives;
    }

    protected void recieveCallsBasedOnPerformance() {
        int index;
        boolean needsEscalation;

        while(callsAttended != number_of_calls) {

            index = findIndexOfBestExecutiveToTakeCall(new ArrayList<Employee>(junior_Executives));

            if(index < 0) {
                //We do not have another available executive to handle the call. Continue!
                callsAttended++;
                continue;
            }
            needsEscalation = junior_Executives.get(index).takeCallAndEscalateIfRequired();

            if(needsEscalation)
            {
                index = findIndexOfBestExecutiveToTakeCall(new ArrayList<>(senior_Executives));

                if(index < 0) {
                    //We do not have another available executive to handle the call. Continue!
                    callsAttended++;
                    continue;
                }

                needsEscalation = senior_Executives.get(index).takeCallAndEscalateIfRequired();

                if(needsEscalation){
                    if(!manager.canTakeTheCall()) {
                        //Manager cannot handle the call. Continue!
                        callsAttended++;
                        continue;
                    }
                    manager.takeCallAndEscalateIfRequired();
                }
            }
            callsAttended++;
        }
    }

    protected int findIndexOfBestExecutiveToTakeCall(List<Employee> listOfExecutives) {
        int index = -1;
        for(Employee executive : listOfExecutives) {
            if(executive.canTakeTheCall()) {
                //get the CallDistributionService of the first executive who can take the call
                index = listOfExecutives.indexOf(executive);
                break;
            }
        }
        for(Employee executive: listOfExecutives) {
            if(executive.canTakeTheCall()) {
                if(executive.timeTakenForNextCall() <= listOfExecutives.get(index).timeTakenForNextCall()) {
                    index = listOfExecutives.indexOf(executive);
                }
            }
        }
        return index;
    }

    protected int getCallsResolvedByThisSampleSet() {
        int resolved = 0;
        for(JuniorExecutive executive: junior_Executives) {
            resolved = resolved + executive.resolved;
        }
        for (SeniorExecutive executive: senior_Executives) {
            resolved = resolved + executive.resolved;
        }
        resolved = resolved + manager.resolved;
        return resolved;
    }

    protected int getTotalTimeTakenInMinutesByThisSampleSet() {
        int totalTimeTakenInMinutes = 0;
        for(JuniorExecutive executive: junior_Executives) {
            totalTimeTakenInMinutes = totalTimeTakenInMinutes + executive.timeTakenInMinutes;
        }
        for (SeniorExecutive executive: senior_Executives) {
            totalTimeTakenInMinutes = totalTimeTakenInMinutes + executive.timeTakenInMinutes;
        }
        totalTimeTakenInMinutes = totalTimeTakenInMinutes + manager.timeTakenInMinutes;
        return totalTimeTakenInMinutes;
    }


    protected void doBasicValidation() throws InvalidRequestException {
        int numberOfJuniorExecutives = junior_Executives.size();
        int numberOfSeniorExecutives = senior_Executives.size();
        int numberOfCallsForExecutives = (number_of_calls)/(numberOfJuniorExecutives)+numberOfSeniorExecutives;

        if(numberOfJuniorExecutives < numberOfSeniorExecutives) {
            throw new InvalidRequestException("Number of junior executives is lesser than number of senior executives!");
        }

        for(JuniorExecutive juniorExecutive : junior_Executives) {
            juniorExecutive.setMaxCallsPossible(numberOfCallsForExecutives);
        }
        for (SeniorExecutive seniorExecutive : senior_Executives) {
            seniorExecutive.setMaxCallsPossible(numberOfCallsForExecutives);
        }
        manager.setMaxCallsPossible(number_of_calls);
    }
}
