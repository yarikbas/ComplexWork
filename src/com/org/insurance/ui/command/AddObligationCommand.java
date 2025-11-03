package com.org.insurance.ui.command;

import com.org.insurance.domain.*;
import com.org.insurance.ui.InsuranceMenu;
import java.util.List;

public class AddObligationCommand implements Command {
    @Override
    public String getDescription() {
        return "Add obligation to derivative";
    }

    @Override
    public void execute(List<Derivative> derivatives) {
        if (derivatives.isEmpty()) {
            System.out.println("No derivatives available. Create one first.");
            return;
        }

        System.out.println("Select derivative:");
        for (int i = 0; i < derivatives.size(); i++) {
            System.out.println(i + ". " + derivatives.get(i).getName());
        }

        System.out.print("Enter derivative number: ");
        int derivChoice = InsuranceMenu.scanner.nextInt();
        Derivative selectedDerivative = derivatives.get(derivChoice);

        Obligation obligation = createObligation();
        selectedDerivative.addObligation(obligation);
        System.out.println("Obligation added to " + selectedDerivative.getName());
    }

    private Obligation createObligation() {
        System.out.println("Select obligation type:");
        System.out.println("1. Business");
        System.out.println("2. Travel");
        System.out.println("3. Property");
        System.out.println("4. Auto");
        System.out.println("5. Life");
        System.out.println("6. Health");
        System.out.println("7. Liability");

        System.out.print("Enter type number: ");
        int typeChoice = InsuranceMenu.scanner.nextInt();

        System.out.print("Enter obligation name: ");
        String name = InsuranceMenu.scanner.nextLine();

        System.out.print("Enter insured amount: ");
        double amount = InsuranceMenu.scanner.nextFloat();

        System.out.print("Enter factor: ");
        double factor = InsuranceMenu.scanner.nextFloat();

        System.out.print("Enter period (years): ");
        int period = InsuranceMenu.scanner.nextInt();

        System.out.print("Enter interest rate: ");
        double interestRate = InsuranceMenu.scanner.nextFloat();

        System.out.print("Enter probability: ");
        double probability = InsuranceMenu.scanner.nextFloat();

        System.out.print("Enter max cost: ");
        double maxCost = InsuranceMenu.scanner.nextFloat();

        switch (typeChoice) {
            case 1:
                return createBusinessObligation(name, amount, factor, period, interestRate, probability, maxCost);
            case 2:
                return createTravelObligation(name, amount, factor, period, interestRate, probability, maxCost);
            case 3:
                return createPropertyObligation(name, amount, factor, period, interestRate, probability, maxCost);
            case 4:
                return createAutoObligation(name, amount, factor, period, interestRate, probability, maxCost);
            case 5:
                return createLifeObligation(name, amount, factor, period, interestRate, probability, maxCost);
            case 6:
                return createHealthObligation(name, amount, factor, period, interestRate, probability, maxCost);
            case 7:
                return createLiabilityObligation(name, amount, factor, period, interestRate, probability, maxCost);
            default:
                throw new IllegalArgumentException("Unknown obligation type");
        }
    }

    private BusinessObligation createBusinessObligation(String name, double amount, double factor, int period,
                                                        double interestRate, double probability, double maxCost) {
        System.out.print("Enter business type: ");
        String businessType = InsuranceMenu.scanner.nextLine();
        System.out.print("Enter revenue: ");
        double revenue = InsuranceMenu.scanner.nextDouble();
        System.out.print("Enter employee count: ");
        int employees = InsuranceMenu.scanner.nextInt();

        return new BusinessObligation(name, amount, factor, period, interestRate, probability, maxCost,
                businessType, revenue, employees);
    }

    private TravelObligation createTravelObligation(String name, double amount, double factor, int period,
                                                    double interestRate, double probability, double maxCost) {
        System.out.print("Enter trip days: ");
        int days = InsuranceMenu.scanner.nextInt();
        System.out.print("Enter destination: ");
        String destination = InsuranceMenu.scanner.nextLine();
        System.out.print("Enter purpose: ");
        String purpose = InsuranceMenu.scanner.nextLine();

        return new TravelObligation(name, amount, factor, period, interestRate, probability, maxCost,
                days, destination, purpose);
    }

    private PropertyObligation createPropertyObligation(String name, double amount, double factor, int period,
                                                        double interestRate, double probability, double maxCost) {
        System.out.print("Enter location: ");
        String location = InsuranceMenu.scanner.nextLine();
        System.out.print("Enter deductible: ");
        double deductible = InsuranceMenu.scanner.nextFloat();
        System.out.print("Enter construction type: ");
        String constructionType = InsuranceMenu.scanner.nextLine();

        return new PropertyObligation(name, amount, factor, period, interestRate, probability, maxCost,
                location, deductible, constructionType);
    }

    private AutoObligation createAutoObligation(String name, double amount, double factor, int period,
                                                double interestRate, double probability, double maxCost) {
        System.out.print("Enter vehicle type: ");
        String vehicleType = InsuranceMenu.scanner.nextLine();
        System.out.print("Enter driver class: ");
        String driverClass = InsuranceMenu.scanner.nextLine();
        System.out.print("Enter bonus malus: ");
        double bonusMalus = InsuranceMenu.scanner.nextFloat();

        return new AutoObligation(name, amount, factor, period, interestRate, probability, maxCost,
                vehicleType, driverClass, bonusMalus);
    }

    private LifeObligation createLifeObligation(String name, double amount, double factor, int period,
                                                double interestRate, double probability, double maxCost) {
        System.out.print("Enter age: ");
        int age = InsuranceMenu.scanner.nextInt();
        System.out.print("Enter term years: ");
        int termYears = InsuranceMenu.scanner.nextInt();

        return new LifeObligation(name, amount, factor, period, interestRate, probability, maxCost,
                age, termYears);
    }

    private HealthObligation createHealthObligation(String name, double amount, double factor, int period,
                                                    double interestRate, double probability, double maxCost) {
        System.out.print("Enter coverage type: ");
        String coverageType = InsuranceMenu.scanner.nextLine();
        System.out.print("Has pre-existing conditions? (true/false): ");
        boolean hasConditions = Boolean.parseBoolean(InsuranceMenu.scanner.nextLine());
        System.out.print("Enter annual limit: ");
        double annualLimit = InsuranceMenu.scanner.nextFloat();

        return new HealthObligation(name, amount, factor, period, interestRate, probability, maxCost,
                coverageType, hasConditions, annualLimit);
    }

    private LiabilityObligation createLiabilityObligation(String name, double amount, double factor, int period,
                                                          double interestRate, double probability, double maxCost) {
        System.out.print("Enter limit per claim: ");
        double limitPerClaim = InsuranceMenu.scanner.nextFloat();
        System.out.print("Enter aggregate limit: ");
        double aggregateLimit = InsuranceMenu.scanner.nextFloat();

        return new LiabilityObligation(name, amount, factor, period, interestRate, probability, maxCost,
                limitPerClaim, aggregateLimit);
    }
}