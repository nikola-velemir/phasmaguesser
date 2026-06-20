package com.ftn.sbnz.model.events;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("3m")
public class IncenceUsedEvent  extends GhostEvent{

    public IncenceUsedEvent(long timestamp) {
        super(timestamp);
    }

    public IncenceUsedEvent() {
        super();
    }

}
