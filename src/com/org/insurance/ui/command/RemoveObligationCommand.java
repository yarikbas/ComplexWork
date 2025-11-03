package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import com.org.insurance.ui.InsuranceMenu;
import java.util.List;

public class RemoveObligationCommand implements Command {
    @Override
    public String getDescription() {
        return "Remove obligation from derivative";
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
        int derivChoice = InsuranceMenu.scanner.nextInt();
        Derivative selectedDerivative = derivatives.get(derivChoice);

        List<Obligation> obligations = selectedDerivative.getItems();
        if (obligations.isEmpty()) {
            System.out.println("No obligations in selected derivative!");
            return;
        }

        System.out.println("Obligations in " + selectedDerivative.getName() + ":");
        for (int i = 0; i < obligations.size(); i++) {
            System.out.println(i + ". " + obligations.get(i).getName());
        }

        System.out.print("Enter obligation number to remove: ");
        int obligChoice = InsuranceMenu.scanner.nextInt();

        if (obligChoice >= 0 && obligChoice < obligations.size()) {
            Obligation removed = obligations.get(obligChoice);
            selectedDerivative.removeObligation(removed.getId());
            System.out.println("Removed: " + removed.getName());
        } else {
            System.out.println("Invalid selection!");
        }
    }
}