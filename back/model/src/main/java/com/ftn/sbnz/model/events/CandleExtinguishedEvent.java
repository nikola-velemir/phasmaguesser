package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("5m")
public class CandleExtinguishedEvent extends GhostEvent {

    public CandleExtinguishedEvent() {
        super();
    }

    public CandleExtinguishedEvent(long timestamp) {
        super(timestamp);
    }

}
