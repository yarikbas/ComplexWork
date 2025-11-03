package com.org.insurance.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Derivative implements Serializable {

    private final UUID id = UUID.randomUUID();
    private String name;
    private List<Obligation> obligations;

    public Derivative() {}

    public Derivative(String name) {
        this.name = name;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public List<Obligation> getObligations() { return obligations; }


    public void setName(String name) { this.name = name; }
    public void setObligations(List<Obligation> obligations) { this.obligations = obligations; }
}
