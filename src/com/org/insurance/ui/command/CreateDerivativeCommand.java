package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.ui.InsuranceMenu;
import com.org.insurance.ui.Inputs;

import java.util.Scanner;

public class CreateDerivativeCommand implements Command {
    private final InsuranceMenu menu;
    public CreateDerivativeCommand(InsuranceMenu menu) { this.menu = menu; }

    @Override public String getDescription() { return "Create new derivative"; }

    @Override
    public void execute(Scanner sc) {
        String name = Inputs.nextLine(sc, "Name: ");
        Derivative d = new Derivative(name);
        menu.addDerivative(d);
        menu.setSelected(d);
        System.out.println("Created & selected: " + name);
    }
}
