package com.org.insurance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Derivative implements Serializable {

    @EqualsAndHashCode.Include
    private final UUID id = UUID.randomUUID();

    private String name;
    private final List<Obligation> items = new ArrayList<>();

    public Derivative(String name) { this.name = name; }

    public void add(Obligation o) {
        if (o != null) items.add(o);
    }

    public boolean remove(UUID obligationId) {
        Iterator<Obligation> it = items.iterator();
        while (it.hasNext()) {
            Obligation o = it.next();
            if (Objects.equals(o.getId(), obligationId)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    /** Повертаємо немодифікований вигляд, щоб не ламали інваріанти */
    public List<Obligation> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getTotalValue() {
        double sum = 0.0;
        for (Obligation o : items) {
            sum += o.calculateValue();
        }
        return sum;
    }

    public double getTotalRisk() {
        double sum = 0.0;
        for (Obligation o : items) {
            sum += o.calculateRisk();
        }
        return sum;
    }

    public List<Obligation> sortedByRiskDesc() {
        List<Obligation> copy = new ArrayList<>(items);
        Collections.sort(copy, new RiskComparator());
        return copy;
    }

    public void sortByRiskDescInPlace() {
        Collections.sort(items, new RiskComparator());
    }

    public List<Obligation> findBy(Predicate<Obligation> p) {
        List<Obligation> out = new ArrayList<>();
        for (Obligation o : items) {
            if (p.test(o)) out.add(o);
        }
        return out;
    }
}
