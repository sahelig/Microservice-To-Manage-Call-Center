package com.callcenter.callcenterimplementation;

public class Manager extends Employee{
    private int unresolved;
    public String getUnresolved() {
        return Integer.toString(unresolved);
    }
    public Manager(String id, int[] listOfMockTimesTaken) {
        super(id,15,listOfMockTimesTaken);
        unresolved = 0;
    }
    public boolean takeCallAndEscalateIfRequired() {
        boolean isCallUnresolved = super.takeCallAndEscalateIfRequired();
        if(isCallUnresolved) {
            unresolved++;
        }
        return isCallUnresolved;
    }
}
