package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("10m")

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
