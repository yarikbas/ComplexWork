package com.org.insurance.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Derivative implements Serializable {
    private static final long serialVersionUID = 1L;

    private final UUID id = UUID.randomUUID();

    private String name;
    private List<Obligation> obligations;

    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public List<Obligation> getObligations() { return obligations; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setObligations(List<Obligation> obligations) { this.obligations = obligations; }
}
