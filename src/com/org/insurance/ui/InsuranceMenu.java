package com.org.insurance.ui;

import com.org.insurance.ui.command.*;
import com.org.insurance.domain.Derivative;
import java.util.*;

public class InsuranceMenu {
    public static Scanner scanner = new Scanner(System.in);
    private Map<String, Command> commands;
    private List<Derivative> derivatives;

    public InsuranceMenu() {
        this.commands = new HashMap<>();
        this.derivatives = new ArrayList<>();
        registerCommands();
    }

    private void registerCommands() {
        registerCommand("create", new CreateDerivativeCommand());
        registerCommand("edit", new EditDerivativeCommand());
        registerCommand("show", new ShowDerivativesCommand());
        registerCommand("add", new AddObligationCommand());
        registerCommand("remove", new RemoveObligationCommand());
        registerCommand("calculate", new CalculateCommand());
        registerCommand("sort", new SortByRiskCommand());
        registerCommand("find", new FindObligationCommand());
        registerCommand("delete", new DeleteDerivativeCommand());
        registerCommand("save", new SaveToFileCommand());
        registerCommand("load", new LoadFromFileCommand());
    }

    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    public void executeCommand(String input) {
        Command command = commands.get(input);
        if (command != null) {
            command.execute(derivatives);
        } else {
            System.out.println("Unknown command: " + input);
        }
    }

    public void run() {
        System.out.println("=== Insurance Management System ===");
        System.out.println("Type 'help' for commands, 'exit' to quit");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) {
                exit();
                break;
            } else if ("help".equalsIgnoreCase(input)) {
                showHelp();
            } else {
                executeCommand(input);
            }
        }
    }

    private void showHelp() {
        System.out.println("Available commands:");
        commands.forEach((key, cmd) ->
                System.out.println("  " + key + " - " + cmd.getDescription()));
        System.out.println("  help - Show this help");
        System.out.println("  exit - Exit program");
    }

    private void exit() {
        System.out.println("Goodbye!");
        scanner.close();
    }

    public List<Derivative> getDerivatives() {
        return derivatives;
    }
}