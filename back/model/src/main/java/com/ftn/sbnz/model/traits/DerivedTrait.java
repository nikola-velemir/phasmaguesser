package com.ftn.sbnz.model.traits;

import java.io.Serializable;

public final class DerivedTrait implements Serializable {
    private String traitName;
    private String source;
    
    public DerivedTrait(String traitName, String source) {
        this.traitName = traitName;
        this.source = source;
    }

    /**
     * @return String return the traitName
     */
    public String getTraitName() {
        return traitName;
    }

    /**
     * @return String return the source
     */
    public String getSource() {
        return source;
    }

}
