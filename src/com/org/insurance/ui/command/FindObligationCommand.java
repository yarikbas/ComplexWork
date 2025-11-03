package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import com.org.insurance.ui.InsuranceMenu;
import java.util.List;

public class FindObligationCommand implements Command {
    @Override
    public String getDescription() {
        return "Find obligations by name in derivative";
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
        Derivative selected = derivatives.get(derivChoice);

        System.out.print("Enter search term: ");
        String term = InsuranceMenu.scanner.nextLine().toLowerCase();

        List<Obligation> found = selected.findObligations(obligation ->
                obligation.getName().toLowerCase().contains(term));

        if (found.isEmpty()) {
            System.out.println("No obligations found with: " + term);
        } else {
            System.out.println("Found " + found.size() + " obligations:");
            for (Obligation obligation : found) {
                System.out.println("- " + obligation.getName() + " (Risk: " + obligation.calculateRisk() + ")");
            }
        }
    }
}