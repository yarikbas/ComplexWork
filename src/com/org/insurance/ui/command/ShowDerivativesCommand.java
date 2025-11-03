package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.ui.InsuranceMenu;
import com.org.insurance.ui.Inputs;

import java.util.List;
import java.util.Scanner;

public class ShowDerivativesCommand implements Command {
    private final InsuranceMenu menu;
    public ShowDerivativesCommand(InsuranceMenu menu) { this.menu = menu; }

    @Override public String getDescription() { return "List derivatives and select one"; }

    @Override
    public void execute(Scanner sc) {
        List<Derivative> list = menu.getDerivatives();
        if (list.isEmpty()) { System.out.println("No derivatives."); return; }
        for (int i = 0; i < list.size(); i++) System.out.println(i + ": " + list.get(i));
        int idx = Inputs.nextInt(sc, "Select index (-1 to skip): ", -1, list.size() - 1);
        if (idx >= 0 && idx < list.size()) {
            menu.setSelected(list.get(idx));
            System.out.println("Selected: " + list.get(idx).getName());
        }
    }
}
