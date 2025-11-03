package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.ui.InsuranceMenu;
import com.org.insurance.ui.Inputs;

import java.util.List;
import java.util.Scanner;

public class DeleteDerivativeCommand implements Command {
    private final InsuranceMenu menu;
    public DeleteDerivativeCommand(InsuranceMenu menu) { this.menu = menu; }

    @Override public String getDescription() { return "Delete derivative by index"; }

    @Override
    public void execute(Scanner sc) {
        List<Derivative> list = menu.getDerivatives();
        if (list.isEmpty()) { System.out.println("No derivatives."); return; }
        for (int i = 0; i < list.size(); i++) System.out.println(i + ": " + list.get(i));
        int idx = Inputs.nextInt(sc, "Index to delete: ", 0, list.size() - 1);
        Derivative removed = list.remove(idx);
        if (removed == menu.getSelected()) menu.setSelected(null);
        System.out.println("Deleted: " + removed.getName());
    }
}
