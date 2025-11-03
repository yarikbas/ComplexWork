package com.org.insurance.domain;

import java.io.Serializable;
import java.util.*;

public class Derivative implements Serializable {
    private static final long serialVersionUID = 1L;

    private final UUID id = UUID.randomUUID();
    private String name;
    private final List<Obligation> items = new ArrayList<Obligation>();

    public Derivative() {}
    public Derivative(String name) { this.name = name == null ? "" : name.trim(); }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name == null ? "" : name.trim(); }

    public void add(Obligation o) { if (o != null) items.add(o); }

    /** Видалити зобовʼязання за UUID. */
    public boolean remove(UUID obligationId) {
        if (obligationId == null) return false;
        for (Iterator<Obligation> it = items.iterator(); it.hasNext(); ) {
            Obligation o = it.next();
            if (obligationId.equals(o.getId())) { it.remove(); return true; }
        }
        return false;
    }

    public List<Obligation> getItems() { return Collections.unmodifiableList(items); }

    public double getTotalValue() {
        double sum = 0.0;
        for (int i = 0; i < items.size(); i++) sum += items.get(i).calculateValue();
        return sum;
    }
    public double getTotalRisk() {
        double sum = 0.0;
        for (int i = 0; i < items.size(); i++) sum += items.get(i).calculateRisk();
        return sum;
    }

    public List<Obligation> sortedByRiskDesc() {
        List<Obligation> copy = new ArrayList<Obligation>(items);
        Collections.sort(copy, new RiskComparator());
        return copy;
    }

    /** Пошук у межах діапазонів; -1 означає «не обмежувати». */
    public List<Obligation> findByRanges(
            Double minAmount, Double maxAmount,
            Double minRisk,   Double maxRisk,
            Double minValue,  Double maxValue
    ) {
        final double EPS = 1e-9;
        List<Obligation> out = new ArrayList<Obligation>();
        for (int i = 0; i < items.size(); i++) {
            Obligation o = items.get(i);
            double amount = o.getInsuredAmount();
            double risk   = o.calculateRisk();
            double value  = o.calculateValue();

            if (minAmount != null && minAmount >= 0 && amount + EPS < minAmount) continue;
            if (maxAmount != null && maxAmount >= 0 && amount > maxAmount + EPS) continue;
            if (minRisk   != null && minRisk   >= 0 && risk   + EPS < minRisk) continue;
            if (maxRisk   != null && maxRisk   >= 0 && risk   > maxRisk + EPS) continue;
            if (minValue  != null && minValue  >= 0 && value  + EPS < minValue) continue;
            if (maxValue  != null && maxValue  >= 0 && value  > maxValue + EPS) continue;

            out.add(o);
        }
        return out;
    }

    @Override public String toString() {
        return "Derivative{" + name + ", items=" + items.size() + "}";
    }
}
