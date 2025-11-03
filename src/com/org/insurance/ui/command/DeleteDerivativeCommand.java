package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.ui.InsuranceMenu;
import java.util.List;

public class DeleteDerivativeCommand implements Command {
    @Override
    public String getDescription() {
        return "Delete derivative";
    }

    @Override
    public void execute(List<Derivative> derivatives) {
        if (derivatives.isEmpty()) {
            System.out.println("No derivatives available");
            return;
        }

        System.out.println("Available derivatives:");
        for (int i = 0; i < derivatives.size(); i++) {
            System.out.println(i + ". " + derivatives.get(i).getName());
        }

        System.out.print("Enter derivative number to delete: ");
        int choice = InsuranceMenu.scanner.nextInt();

        if (choice >= 0 && choice < derivatives.size()) {
            Derivative toDelete = derivatives.get(choice);
            derivatives.remove(choice);
            System.out.println("Deleted: " + toDelete.getName());
        } else {
            System.out.println("Invalid selection!");
        }
    }
}