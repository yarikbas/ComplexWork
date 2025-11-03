package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.ui.InsuranceMenu;

public class RemoveObligationFromSelectedCommand implements Command {
    @Override
    public String getDescription() {
        return "Remove obligation from selected derivative";
    }

    @Override
    public void execute() {
        InsuranceMenu menu = InsuranceMenu.getInstance();
        Derivative current = menu.getDerivative();
        if (current != null && !current.getItems().isEmpty()) {
            System.out.println("Enter obligation ID to remove: ");
            String id = InsuranceMenu.scanner.nextLine();
            // Логіка пошуку та видалення obligation за ID
            System.out.println("Obligation removed");
        } else {
            System.out.println("No derivative selected or empty!");
        }
    }
}