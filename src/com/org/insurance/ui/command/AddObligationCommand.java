package com.org.insurance.ui.command;

import com.org.insurance.domain.*;
import java.util.List;
import java.util.Scanner;

public class AddObligationCommand implements Command {

    @Override
    public String getDescription() {
        return "Додати облігацію: оберіть деривативу, потім тип облігації — і вона буде додана";
    }

    @Override
    public void execute(Scanner in, List<Derivative> derivatives) {
        Derivative d = pickDerivative(in, derivatives);
        if (d == null) return;

        int type = pickType(in);
        if (type < 1 || type > 7) {
            System.out.println("Невірний вибір типу.");
            return;
        }

        Obligation o = createByType(type);

        d.getObligations().add(o);
        System.out.println("Додано облігацію типу " + o.getClass().getSimpleName() +
                " до деривативи: " + (d.getName() != null ? d.getName() : d.getId()));
    }

    private Derivative pickDerivative(Scanner in, List<Derivative> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Немає дериватив для додавання.");
            return null;
        }
        System.out.println("Оберіть деривативу:");
        for (int i = 0; i < list.size(); i++) {
            Derivative d = list.get(i);
            String name = d.getName() != null ? d.getName() : "без назви";
            System.out.printf("%d) %s (%s)%n", i + 1, name, d.getId());
        }
        System.out.print("> №: ");
        int idx = readInt(in);
        if (idx < 1 || idx > list.size()) return null;
        return list.get(idx - 1);
    }

    private int pickType(Scanner in) {
        System.out.println("Оберіть тип облігації для додавання:");
        System.out.println("1) auto");
        System.out.println("2) business");
        System.out.println("3) health");
        System.out.println("4) liability");
        System.out.println("5) life");
        System.out.println("6) property");
        System.out.println("7) travel");
        System.out.print("> №: ");
        return readInt(in);
    }

    private Obligation createByType(int type) {
        switch (type) {
            case 1: return new AutoObligation();
            case 2: return new BusinessObligation();
            case 3: return new HealthObligation();
            case 4: return new LiabilityObligation();
            case 5: return new LifeObligation();
            case 6: return new PropertyObligation();
            case 7: return new TravelObligation();
            default: throw new IllegalArgumentException();
        }
    }

    private int readInt(Scanner in) {
        try { return Integer.parseInt(in.nextLine().trim()); }
        catch (Exception e) { return -1; }
    }
}
