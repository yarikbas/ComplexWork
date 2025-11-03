package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.ui.InsuranceMenu;
import java.util.List;

public class CreateDerivativeCommand implements Command {
    @Override
    public String getDescription() {
        return "Create new derivative";
    }

    @Override
    public void execute(List<Derivative> derivatives) {
        System.out.print("Enter derivative name: ");
        String name = InsuranceMenu.scanner.nextLine();
        Derivative derivative = new Derivative(name);
        derivatives.add(derivative);
        System.out.println("Derivative created: " + name);
    }
}