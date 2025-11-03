package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;

import java.util.*;
import java.util.Scanner;

public class RemoveObligationCommand implements Command {
    @Override public String getDescription() {
        return "Видалити одну/кілька облігацій (за індексом/діапазоном/UUID) з вибраного деривативу";
    }

    @Override
    public void execute(Scanner in, List<Derivative> derivatives) {
        Derivative d = pickDerivative(in, derivatives);
        if (d == null) return;

        var obs = d.getObligations();
        if (obs == null || obs.isEmpty()) {
            System.out.println("У деривативі немає облігацій.");
            return;
        }

        // 1) показати всі облігації
        System.out.println("Облігації деривативи:");
        for (int i = 0; i < obs.size(); i++) {
            Obligation o = obs.get(i);
            String name = (o.getName() != null && !o.getName().isBlank()) ? o.getName() : "—";
            String type = o.getClass().getSimpleName();
            System.out.printf("%2d) %-22s  %-14s  (%s)%n", i + 1, name, type, o.getId());
        }

        // 2) зчитати вибір
        System.out.println("""
                Введіть №(и) або UUID для видалення:
                 приклади: 3
                           1,3,5
                           2-4,7
                           1, 2-3, 93b9...-uuid
                """);
        System.out.print("> ");
        String input = in.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Скасовано.");
            return;
        }

        // 3) розібрати індекси/діапазони та UUID
        Selection sel = parseSelection(input, obs.size());
        if (sel.isEmpty()) {
            System.out.println("Не знайдено коректних позицій або UUID для видалення.");
            return;
        }

        // 4) підтвердження
        System.out.printf("Підтвердити видалення (%d за індексами, %d за UUID) [y/N]: ",
                sel.indexes.size(), sel.uuids.size());
        String confirm = in.nextLine().trim().toLowerCase(Locale.ROOT);
        if (!confirm.equals("y") && !confirm.equals("yes")) {
            System.out.println("Скасовано.");
            return;
        }

        // 5) видаляти: спершу за індексами (у зворотному порядку), потім UUID
        int removed = 0;

        if (!sel.indexes.isEmpty()) {
            List<Integer> idxs = new ArrayList<>(sel.indexes);
            idxs.sort(Comparator.reverseOrder()); // щоб не зсувались
            for (int idx1 : idxs) {
                int idx0 = idx1 - 1;
                if (idx0 >= 0 && idx0 < obs.size()) {
                    Obligation o = obs.remove(idx0);
                    removed++;
                    System.out.println("Видалено (index): " + safeName(o) + " (" + o.getId() + ")");
                }
            }
        }

        if (!sel.uuids.isEmpty()) {
            // зручно видаляти по одному uuid, щоб показати що саме зникло
            for (UUID id : sel.uuids) {
                // знайдемо першу відповідність
                int pos = -1;
                for (int i = 0; i < obs.size(); i++) {
                    if (obs.get(i).getId().equals(id)) { pos = i; break; }
                }
                if (pos != -1) {
                    Obligation o = obs.remove(pos);
                    removed++;
                    System.out.println("Видалено (uuid):  " + safeName(o) + " (" + id + ")");
                } else {
                    System.out.println("Не знайдено (uuid): " + id);
                }
            }
        }

        // 6) підсумок
        System.out.println("Разом видалено: " + removed);
        if (obs.isEmpty()) {
            System.out.println("У деривативі не залишилося облігацій.");
        }
    }

    // ---------- helpers ----------
    private Derivative pickDerivative(Scanner in, List<Derivative> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Немає деривативів.");
            return null;
        }
        System.out.println("Оберіть деривативу:");
        for (int i = 0; i < list.size(); i++) {
            var d = list.get(i);
            System.out.printf("%d) %s (%s)%n", i + 1, d.getName() != null ? d.getName() : "без назви", d.getId());
        }
        System.out.print("> №: ");
        try {
            int idx = Integer.parseInt(in.nextLine().trim());
            return (idx >= 1 && idx <= list.size()) ? list.get(idx - 1) : null;
        } catch (Exception e) {
            return null;
        }
    }

    private static String safeName(Obligation o) {
        return (o.getName() != null && !o.getName().isBlank()) ? o.getName() : o.getClass().getSimpleName();
    }

    /** Контейнер вибраних позицій. */
    private static final class Selection {
        final Set<Integer> indexes = new HashSet<>(); // 1-базовані
        final List<UUID> uuids = new ArrayList<>();
        boolean isEmpty() { return indexes.isEmpty() && uuids.isEmpty(); }
    }

    /** Розібрати рядок на 1-базовані індекси (числа/діапазони) і UUID (кома/пробіли не важливі). */
    private static Selection parseSelection(String input, int maxIndex) {
        Selection sel = new Selection();

        // Розіб'ємо по комах і пробілах
        String[] tokens = input.split("[,\\s]+");
        for (String t : tokens) {
            String token = t.trim();
            if (token.isEmpty()) continue;

            // Діапазон a-b
            int dash = token.indexOf('-');
            if (dash > 0 && dash < token.length()-1) {
                String left = token.substring(0, dash).trim();
                String right = token.substring(dash + 1).trim();
                try {
                    int a = Integer.parseInt(left);
                    int b = Integer.parseInt(right);
                    if (a > b) { int tmp = a; a = b; b = tmp; }
                    for (int i = a; i <= b; i++) {
                        if (i >= 1 && i <= maxIndex) sel.indexes.add(i);
                    }
                    continue;
                } catch (NumberFormatException ignored) {
                }
            }

            try {
                int idx = Integer.parseInt(token);
                if (idx >= 1 && idx <= maxIndex) sel.indexes.add(idx);
                continue;
            } catch (NumberFormatException ignored) { }

            try {
                sel.uuids.add(UUID.fromString(token));
            } catch (IllegalArgumentException ignored) { }

        }

        return sel;
    }
}
