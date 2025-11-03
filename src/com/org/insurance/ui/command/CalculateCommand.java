package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.service.InsuranceCalculator;
import com.org.insurance.ui.InsuranceMenu;
import java.util.List;

public class CalculateCommand implements Command {
    @Override
    public String getDescription() {
        return "Perform various calculations on derivatives";
    }

    @Override
    public void execute(List<Derivative> derivatives) {
        if (derivatives.isEmpty()) {
            System.out.println("No derivatives available.");
            return;
        }

        InsuranceCalculator calculator = new InsuranceCalculator();

        System.out.println("Select calculation type:");
        System.out.println("1. Calculate portfolio value");
        System.out.println("2. Calculate total risk");
        System.out.println("3. Calculate service price for obligation");

        System.out.print("Enter calculation type: ");
        int calcType = Integer.parseInt(InsuranceMenu.scanner.nextLine());

        switch (calcType) {
            case 1:
                calculatePortfolioValue(derivatives, calculator);
                break;
            case 2:
                calculateTotalRisk(derivatives, calculator);
                break;
            case 3:
                calculateServicePrice(derivatives, calculator);
                break;
            default:
                System.out.println("Invalid calculation type!");
        }
    }

    private void calculatePortfolioValue(List<Derivative> derivatives, InsuranceCalculator calculator) {
        System.out.println("Select derivative:");
        for (int i = 0; i < derivatives.size(); i++) {
            System.out.println(i + ". " + derivatives.get(i).getName());
        }

        System.out.print("Enter derivative number: ");
        int choice = Integer.parseInt(InsuranceMenu.scanner.nextLine());
        Derivative selected = derivatives.get(choice);

        double value = calculator.calculatePortfolioValue(selected);
        System.out.println("Total portfolio value of " + selected.getName() + ": " + value);
    }

    private void calculateTotalRisk(List<Derivative> derivatives, InsuranceCalculator calculator) {
        System.out.println("Select derivative:");
        for (int i = 0; i < derivatives.size(); i++) {
            System.out.println(i + ". " + derivatives.get(i).getName());
        }

        System.out.print("Enter derivative number: ");
        int choice = Integer.parseInt(InsuranceMenu.scanner.nextLine());
        Derivative selected = derivatives.get(choice);

        double risk = calculator.calculateTotalRisk(selected);
        System.out.println("Total risk of " + selected.getName() + ": " + risk);
    }

    private void calculateServicePrice(List<Derivative> derivatives, InsuranceCalculator calculator) {

        System.out.println("Select derivative:");
        for (int i = 0; i < derivatives.size(); i++) {
            System.out.println(i + ". " + derivatives.get(i).getName());
        }

        System.out.print("Enter derivative number: ");
        int derivChoice = Integer.parseInt(InsuranceMenu.scanner.nextLine());
        Derivative selectedDerivative = derivatives.get(derivChoice);

        if (selectedDerivative.getItems().isEmpty()) {
            System.out.println("No obligations in selected derivative!");
            return;
        }

        System.out.println("Select obligation:");
        for (int i = 0; i < selectedDerivative.getItems().size(); i++) {
            System.out.println(i + ". " + selectedDerivative.getItems().get(i).getName());
        }

        System.out.print("Enter obligation number: ");
        int obligChoice = Integer.parseInt(InsuranceMenu.scanner.nextLine());
        var selectedObligation = selectedDerivative.getItems().get(obligChoice);

        double servicePrice = calculator.calculatePriceOfService(selectedObligation);
        System.out.println("Service price for " + selectedObligation.getName() + ": " + servicePrice);
    }
}