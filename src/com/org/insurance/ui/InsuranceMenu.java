package com.org.insurance.ui;

import com.org.insurance.domain.Derivative;
import com.org.insurance.ui.command.*;

import java.util.*;

public class InsuranceMenu {

    private final List<Derivative> derivatives;
    private final Scanner in;
    private final LinkedHashMap<String, Command> commands;
    private boolean running;

    public InsuranceMenu() {
        this.derivatives = new ArrayList<>();
        this.in = new Scanner(System.in);
        this.commands = new LinkedHashMap<>();
        registerBuiltInCommands();
    }

    public void run() {
        running = true;
        System.out.println("Введіть номер команди. Введіть 'help' для описів, 'exit' — щоб вийти.");
        while (running) {
            showShortMenu();
            System.out.print("> ");
            String line = in.hasNextLine() ? in.nextLine().trim() : null;
            if (line == null) break;
            if (line.isEmpty()) continue;
            executeCommand(line);
        }
    }

    public void executeCommand(String input) {
        String s = input.trim();

        if (s.equalsIgnoreCase("help")) { showHelp(); return; }
        if (s.equalsIgnoreCase("exit") || s.equalsIgnoreCase("quit")) { exit(); return; }

        Integer idx = tryParseInt(s);
        if (idx != null) {
            Command cmd = getCommandByIndex(idx);
            if (cmd == null) {
                System.out.println("Невірний номер. Спробуйте ще.");
                return;
            }
            cmd.execute(in, derivatives);
            return;
        }

        System.out.println("Невідоме введення. Оберіть номер або введіть 'help'.");
    }

    public void showHelp() {
        if (commands.isEmpty()) {
            System.out.println("Команди не зареєстровані.");
            return;
        }
        System.out.println("ОПИС КОМАНД:");
        int i = 1;
        for (Map.Entry<String, Command> e : commands.entrySet()) {
            System.out.printf("%2d) %-12s — %s%n",
                    i++, e.getKey(), e.getValue().getDescription());
        }
        System.out.println("Доступні також: help, exit");
    }


    public void exit() {
        running = false;
        System.out.println("Завершення роботи...");
    }

    private void showShortMenu() {
        if (commands.isEmpty()) {
            System.out.println("[немає зареєстрованих команд]");
            return;
        }
        System.out.println("\nКОМАНДИ:");
        int i = 1;
        for (String key : commands.keySet()) {
            System.out.printf("%2d) %s%n", i++, key);
        }
        System.out.println("help — описи,  exit — вихід");
    }

    private Command getCommandByIndex(int index1based) {
        if (index1based < 1 || index1based > commands.size()) return null;
        int i = 1;
        for (Command c : commands.values()) {
            if (i == index1based) return c;
            i++;
        }
        return null;
    }
    private static Integer tryParseInt(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return null; }
    }

    private void registerBuiltInCommands() {
        registerCommand("add",    new AddObligationCommand());
        registerCommand("calc",   new CalculateCommand());
        registerCommand("create", new CreateDerivativeCommand());
        registerCommand("delete", new DeleteDerivativeCommand());
        registerCommand("find",   new FindObligationCommand());
        registerCommand("load",   new LoadFromFileCommand());
        registerCommand("remove", new RemoveObligationCommand());
        registerCommand("save",   new SaveToFileCommand());
        registerCommand("show",   new ShowDerivativesCommand());
        registerCommand("sort",   new SortByRiskCommand());
    }

    public void registerCommand(String name, Command command) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(command, "command");
        commands.put(name.trim().toLowerCase(Locale.ROOT), command);
    }
}
