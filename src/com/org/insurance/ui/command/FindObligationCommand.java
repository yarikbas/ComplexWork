package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import com.org.insurance.ui.InsuranceMenu;

import java.util.List;

public class FindInSelectedCommand implements Command {
    @Override
    public String getDescription() {
        return "Find obligations in selected derivative";
    }

    @Override
    public void execute() {
        InsuranceMenu menu = InsuranceMenu.getInstance();
        Derivative current = menu.getDerivative();
        if (current != null) {
            System.out.print("Enter search term: ");
            String term = InsuranceMenu.scanner.nextLine();
            List<Obligation> found = current.findObligations(obligation ->
                    obligation.getName().toLowerCase().contains(term.toLowerCase()));
            System.out.println("Found " + found.size() + " obligations");
        } else {
            System.out.println("No derivative selected!");
        }
    }
}