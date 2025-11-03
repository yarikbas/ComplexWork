package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import com.org.insurance.ui.InsuranceMenu;
import com.org.insurance.ui.Inputs;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class RemoveObligationCommand implements Command {
    private final InsuranceMenu menu;
    public RemoveObligationCommand(InsuranceMenu menu) { this.menu = menu; }

    @Override public String getDescription() { return "Remove obligation from selected derivative"; }

    @Override
    public void execute(Scanner sc) {
        Derivative d = menu.getSelected();
        if (d == null) { System.out.println("No selected derivative."); return; }
        List<Obligation> list = d.getItems();
        if (list.isEmpty()) { System.out.println("No obligations."); return; }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ": " + list.get(i).getName() + " | id=" + list.get(i).getId());
        }
        int mode = Inputs.nextInt(sc, "1) by index  2) by UUID : ", 1, 2);
        boolean ok = false;
        if (mode == 1) {
            int idx = Inputs.nextInt(sc, "index: ", 0, list.size() - 1);
            ok = d.remove(list.get(idx).getId());
        } else {
            String s = Inputs.nextLine(sc, "uuid: ");
            try { ok = d.remove(UUID.fromString(s.trim())); } catch (IllegalArgumentException ignored) {}
        }
        System.out.println(ok ? "Removed." : "Not found.");
    }
}
