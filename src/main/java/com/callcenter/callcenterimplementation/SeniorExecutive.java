package com.callcenter.callcenterimplementation;

public class SeniorExecutive extends Employee{
    private int escalated;
    public String getEscalated() {
        return Integer.toString(escalated);
    }
    public SeniorExecutive(String id, int[] mockOfTimeTaken) {
        super(id,10,mockOfTimeTaken);
        escalated = 0;
    }
    public boolean takeCallAndEscalateIfRequired() {
        boolean isEscalationRequired = super.takeCallAndEscalateIfRequired();
        if(isEscalationRequired) {
            escalated++;
        }
        return isEscalationRequired;
    }
}
