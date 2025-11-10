package com.org.insurance.ui;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import com.org.insurance.service.RiskComparator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class ConsolePrinter {

    private ConsolePrinter() {}

    public static void printDerivatives(List<Derivative> derivatives) {
        List<Derivative> list = safeList(derivatives);
        if (list.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Derivative d = list.get(i);
            System.out.printf("%d) %s%n", i + 1, derivativeLine(d));
        }
    }

    public static void printDerivativesWithObligations(List<Derivative> derivatives) {
        List<Derivative> list = safeList(derivatives);
        if (list.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Derivative d = list.get(i);
            System.out.printf("%d) %s%n", i + 1, derivativeHeader(d));

            List<Obligation> obs = safeList(d.getObligations());
            for (int j = 0; j < obs.size(); j++) {
                Obligation o = obs.get(j);
                System.out.print(o);
            }
        }
    }

    public static void printObligationsOf(Derivative d) {
        if (d == null) {
            System.out.println("Дериватив не обрано.");
            return;
        }
        List<Obligation> list = safeList(d.getObligations());
        if (list.isEmpty()) {
            System.out.println("Зобов'язань немає.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, obligationLineTypeRiskId(list.get(i)));
        }
    }

    private static String derivativeLine(Derivative d) {
        String name = safe(d != null ? d.getName() : null, "без назви");
        String id = shortUuid(d != null ? d.getId() : null);
        return name + " (" + id + ")";
    }

    private static String derivativeHeader(Derivative d) {
        int count = Optional.ofNullable(d)
                .map(Derivative::getObligations)
                .map(List::size).orElse(0);
        return derivativeLine(d) + " — " + count + " зобов'язань";
    }

    private static String obligationLineTypeRiskId(Obligation o) {
        if (o == null) return "—";
        String type = o.getClass().getSimpleName().replace("Obligation", "");
        double risk = RiskComparator.riskScore(o);
        String id = shortUuid(o.getId());
        return type + " | risk=" + formatRisk(risk) + " | id=" + id;
    }

    private static <T> List<T> safeList(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    private static String safe(String s, String fallback) {
        return (s == null || s.isBlank()) ? fallback : s;
    }

    private static String shortUuid(UUID id) {
        if (id == null) return "—";
        String s = id.toString();
        int p = s.indexOf('-');
        return p > 0 ? s.substring(0, p) : s;
    }

    private static String formatRisk(double r) {
        return String.format(java.util.Locale.ROOT, "%.3f", r);
    }
}
