package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import com.org.insurance.service.RiskComparator;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortByRiskCommand implements Command {

    private final RiskComparator comparator = new RiskComparator();

    @Override
    public String getDescription() {
        return "Сортувати облігації у деривативі за зменшенням ризику (RiskComparator як поле)";
    }

    @Override
    public void execute(Scanner in, List<Derivative> derivatives) {
        Derivative d = pickDerivative(in, derivatives);
        if (d == null) return;

        List<Obligation> obs = d.getObligations();
        if (obs == null || obs.isEmpty()) {
            System.out.println("Порожньо."); return;
        }

        Collections.sort(obs, comparator.reversed());

        System.out.println("Відсортовано (risk ↓):");
        for (int i = 0; i < obs.size(); i++) {
            Obligation o = obs.get(i);
            double r = RiskComparator.riskScore(o);
            System.out.printf("%2d) %-20s  risk=%.6f%n",
                    i + 1, (o.getName() != null ? o.getName() : "—"), r);
        }
    }

    private Derivative pickDerivative(Scanner in, List<Derivative> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Немає деривативів."); return null; }
        for (int i = 0; i < list.size(); i++) System.out.printf("%d) %s%n", i + 1, nameOf(list.get(i)));
        System.out.print("> №: ");
        try {
            int idx = Integer.parseInt(in.nextLine().trim());
            return (idx>=1&&idx<=list.size())?list.get(idx-1):null; }
        catch (Exception e) { return null; }
    }
    private static String nameOf(Derivative d){
        return d.getName()!=null?d.getName():"без назви"; }
}
