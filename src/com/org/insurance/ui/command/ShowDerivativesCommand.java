package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import java.util.List;

public class ShowDerivativesCommand implements Command {
    @Override
    public String getDescription() {
        return "Show all derivatives";
    }

    @Override
    public void execute(List<Derivative> derivatives) {
        if (derivatives.isEmpty()) {
            System.out.println("No derivatives available");
        } else {
            System.out.println("Available derivatives:");
            for (int i = 0; i < derivatives.size(); i++) {
                Derivative deriv = derivatives.get(i);
                System.out.println(i + ". " + deriv.getName() +
                        " (" + deriv.getItems().size() + " obligations)");
            }
        }
    }
}