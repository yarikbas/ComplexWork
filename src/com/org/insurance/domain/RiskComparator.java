package com.org.insurance.domain;

import java.util.Comparator;

public class RiskComparator implements Comparator<Obligation> {
    @Override
    public int compare(Obligation o1, Obligation o2) {
        return Double.compare(o2.calculateRisk(), o1.calculateRisk());
    }
}
