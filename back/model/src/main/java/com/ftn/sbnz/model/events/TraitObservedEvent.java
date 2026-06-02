package com.ftn.sbnz.model.events;

public class TraitObservedEvent extends GhostEvent{
    private String traitName;

    public TraitObservedEvent(long timestamp, String traitName) {
        super(timestamp);
        this.traitName = traitName;
    }

    public TraitObservedEvent(String traitName) {
        super();
        this.traitName = traitName;
    }

    public String getTraitName() {
        return traitName;
    }


}
