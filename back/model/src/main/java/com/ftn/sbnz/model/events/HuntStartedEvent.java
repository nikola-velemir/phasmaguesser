package com.ftn.sbnz.model.events;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("120s")
public class HuntStartedEvent extends GhostEvent{

    public HuntStartedEvent(long timestamp) {
        super(timestamp);
    }

    public HuntStartedEvent() {
        super();
    }

}
