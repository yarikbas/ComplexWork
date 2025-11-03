package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import com.org.insurance.service.RiskComparator;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortByRiskCommand implements Command {

    private final Comparator<Obligation> comparator;

    /** Дефолт: використовуємо стандартну формулу ризику. */
    public SortByRiskCommand() {
        this(new RiskComparator());
    }

    /** Можна підкласти інший компаратор (для тестів або іншої формули). */
    public SortByRiskCommand(Comparator<Obligation> comparator) {
        this.comparator = comparator;
    }

    @Override
    public String getDescription() {
        return "Сортувати облігації у деривативі за зменшенням ризику (через RiskComparator)";
    }

    @Override
    public void execute(Scanner in, List<Derivative> derivatives) {
        Derivative d = pickDerivative(in, derivatives);
        if (d == null) return;

        var obs = d.getObligations();
        if (obs == null || obs.isEmpty()) {
            System.out.println("Порожньо.");
            return;
        }

        obs.sort(comparator.reversed()); // ↓ ризик

        System.out.println("Відсортовано (risk ↓):");
        for (int i = 0; i < obs.size(); i++) {
            Obligation o = obs.get(i);
            double r = (comparator instanceof RiskComparator rc)
                    ? RiskComparator.riskScore(o) // показуємо ту ж метрику
                    : Double.NaN;
            System.out.printf("%2d) %-20s  risk=%s%n",
                    i + 1,
                    o.getName() != null ? o.getName() : "—",
                    (Double.isNaN(r) ? "—" : String.format("%.6f", r)));
        }
    }

    private Derivative pickDerivative(Scanner in, List<Derivative> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Немає деривативів.");
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, nameOf(list.get(i)));
        }
        System.out.print("> №: ");
        try {
            int idx = Integer.parseInt(in.nextLine().trim());
            return (idx >= 1 && idx <= list.size()) ? list.get(idx - 1) : null;
        } catch (Exception e) {
            return null;
        }
    }

    private static String nameOf(Derivative d) {
        return d.getName() != null ? d.getName() : "без назви";
    }
}
