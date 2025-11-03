package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import com.org.insurance.ui.InsuranceMenu;
import java.util.List;

public class SortByRiskCommand implements Command {
    @Override
    public String getDescription() {
        return "Sort obligations by risk in derivative";
    }

    @Override
    public void execute(List<Derivative> derivatives) {
        if (derivatives.isEmpty()) {
            System.out.println("No derivatives available.");
            return;
        }

        System.out.println("Select derivative:");
        for (int i = 0; i < derivatives.size(); i++) {
            System.out.println(i + ". " + derivatives.get(i).getName());
        }

        System.out.print("Enter derivative number: ");
        int choice = Integer.parseInt(InsuranceMenu.scanner.nextLine());
        Derivative selected = derivatives.get(choice);

        List<Obligation> sorted = selected.sortedByRiskDesc();
        System.out.println("Obligations sorted by risk (highest first):");
        for (Obligation obligation : sorted) {
            System.out.printf("%s - Risk: %.2f, Value: %.2f\n",
                    obligation.getName(), obligation.calculateRisk(), obligation.calculateValue());
        }
    }
}