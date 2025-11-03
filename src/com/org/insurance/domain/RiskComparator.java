package com.org.insurance.domain;

import java.util.Comparator;
import java.util.UUID;

public class RiskComparator implements Comparator<Obligation> {
    @Override
    public int compare(Obligation a, Obligation b) {
        int byRisk = Double.compare(b.calculateRisk(), a.calculateRisk()); // спадання
        if (byRisk != 0) return byRisk;
        UUID x = a.getId(), y = b.getId();
        return x.compareTo(y);
    }
}
