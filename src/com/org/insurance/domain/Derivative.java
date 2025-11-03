package com.org.insurance.domain;

import lombok.Data;
import java.util.*;
import java.util.UUID;

@Data
public class Derivative {
    private final UUID id = UUID.randomUUID();
    private String name;
    private List<Obligation> items = new ArrayList<>();

    public Derivative(String name) {
        this.name = name;
    }

    public void addObligation(Obligation obligation) {
        items.add(obligation);
    }

    public void removeObligation(UUID id) {
        items.removeIf(obligation -> obligation.getId().equals(id));
    }

    public double getTotalValue() {
        return items.stream()
                .mapToDouble(Obligation::calculateValue)
                .sum();
    }

    public List<Obligation> sortedByRiskDesc() {
        return items.stream()
                .sorted((o1, o2) -> Double.compare(o2.calculateRisk(), o1.calculateRisk()))
                .toList();
    }

    public List<Obligation> findObligations(java.util.function.Predicate<Obligation> predicate) {
        return items.stream()
                .filter(predicate)
                .toList();
    }
}