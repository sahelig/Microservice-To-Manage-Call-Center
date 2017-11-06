package com.callcenter.callcenterimplementation;

public abstract class Employee {
    protected String id = "";
    protected transient int maxCallsPossible;
    protected transient int maxCallTime;
    protected int timeTakenInMinutes;
    protected int callsAttended;
    protected int resolved;
    protected transient int listOfMockTimesTaken[];

    public Employee(String id, int maxCallTime, int[] listOfMockTimesTaken) {
        this.id = id;
        this.maxCallTime = maxCallTime;
        this.listOfMockTimesTaken = listOfMockTimesTaken;
        this.timeTakenInMinutes = 0;
        this.callsAttended = 0;
        this.resolved = 0;
    }

    public String getId() {
        return id;
    }

    public String getTimeTakenInMinutes() {
        return Integer.toString(timeTakenInMinutes);
    }

    public String getCallsAttended() {
        return Integer.toString(callsAttended);
    }

    public String getResolved() {
        return Integer.toString(resolved);
    }

    public void setMaxCallsPossible(int maxCallsPossible) {
        this.maxCallsPossible = (maxCallsPossible > listOfMockTimesTaken.length)
                ? listOfMockTimesTaken.length : maxCallsPossible;
    }

    public boolean canTakeTheCall() {
        if(callsAttended < maxCallsPossible) {
            return true;
        }
       return false;
    }

    public int timeTakenForNextCall() {
        return listOfMockTimesTaken[callsAttended];
    }

    protected boolean takeCallAndEscalateIfRequired() {
        int actualTimeSpent = listOfMockTimesTaken[callsAttended];
        timeTakenInMinutes = timeTakenInMinutes + actualTimeSpent;
        if(listOfMockTimesTaken[callsAttended] <= maxCallTime ) {
            resolved = resolved + 1;
            callsAttended = callsAttended + 1;
            return false;
        }
        callsAttended = callsAttended + 1;
        return true;
    }
}
