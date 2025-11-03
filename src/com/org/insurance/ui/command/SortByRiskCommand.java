package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.domain.Obligation;
import com.org.insurance.ui.InsuranceMenu;

import java.util.List;

public class SortSelectedByRiskCommand implements Command {
    @Override
    public String getDescription() {
        return "Sort obligations in selected derivative by risk";
    }

    @Override
    public void execute() {
        InsuranceMenu menu = InsuranceMenu.getInstance();
        Derivative current = menu.getDerivative();
        if (current != null) {
            List<Obligation> sorted = current.sortedByRiskDesc();
            System.out.println("Obligations sorted by risk:");
            for (Obligation obligation : sorted) {
                System.out.println(obligation.getName() + " - Risk: " + obligation.calculateRisk());
            }
        } else {
            System.out.println("No derivative selected!");
        }
    }
}