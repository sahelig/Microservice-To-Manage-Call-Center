package com.callcenter.callcenterimplementation;

public class JuniorExecutive extends Employee {
    private int escalated;
    public String getEscalated() {
        return Integer.toString(escalated);
    }

    public JuniorExecutive(String id, int[] mockTimingForCalls) {
        super(id,7,mockTimingForCalls);
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
