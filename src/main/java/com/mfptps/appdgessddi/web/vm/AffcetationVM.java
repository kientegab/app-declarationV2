package com.mfptps.appdgessddi.web.vm;

public class AffcetationVM {
    
    private String username;
    private Long structureId;

    public AffcetationVM() {
    }

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
}
