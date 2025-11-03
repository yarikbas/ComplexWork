package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.ui.InsuranceMenu;
import java.util.List;

public class EditDerivativeCommand implements Command {
    @Override
    public String getDescription() {
        return "Edit derivative name";
    }

    @Override
    public void execute(List<Derivative> derivatives) {
        if (derivatives.isEmpty()) {
            System.out.println("No derivatives available.");
            return;
        }

        System.out.println("Select derivative to edit:");
        for (int i = 0; i < derivatives.size(); i++) {
            System.out.println(i + ". " + derivatives.get(i).getName());
        }

        System.out.print("Enter derivative number: ");
        int choice = InsuranceMenu.scanner.nextInt();

        if (choice >= 0 && choice < derivatives.size()) {
            Derivative derivative = derivatives.get(choice);
            System.out.print("Enter new name: ");
            String newName = InsuranceMenu.scanner.nextLine();
            derivative.setName(newName);
            System.out.println("Derivative renamed to: " + newName);
        } else {
            System.out.println("Invalid selection!");
        }
    }
}