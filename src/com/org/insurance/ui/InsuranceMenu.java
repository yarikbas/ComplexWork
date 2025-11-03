package com.org.insurance.ui;

import com.org.insurance.domain.Derivative;
import com.org.insurance.ui.command.*;

import java.util.*;

public class InsuranceMenu {

    // --- поля ---
    private final List<Derivative> derivatives;
    private final Scanner in;
    private final Map<String, Command> commands;

    private boolean running;

    // --- конструктор: реєструємо команди ---
    public InsuranceMenu() {
        this.derivatives = new ArrayList<>();
        this.in = new Scanner(System.in);
        this.commands = new LinkedHashMap<>();
        registerBuiltInCommands();
    }

    // --- API ---
    public void run() {
        running = true;
        System.out.println("Введіть 'help' для списку команд, 'exit' — щоб вийти.");
        while (running) {
            System.out.print("> ");
            String line = in.hasNextLine() ? in.nextLine().trim() : null;
            if (line == null) break;
            if (line.isEmpty()) continue;
            executeCommand(line);
        }
    }

    public void registerCommand(String name, Command command) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(command, "command");
        commands.put(name.trim().toLowerCase(Locale.ROOT), command);
    }

    public void executeCommand(String input) {
        String name = input.trim().toLowerCase(Locale.ROOT);

        if ("help".equals(name)) { showHelp(); return; }
        if ("exit".equals(name) || "quit".equals(name)) { exit(); return; }

        Command cmd = commands.get(name);
        if (cmd == null) {
            System.out.println("Невідома команда: " + input);
            showHelp();
            return;
        }

        System.out.println("→ " + name + ":");
        System.out.println(cmd.getDescription());
        cmd.execute(in, derivatives); // ГОЛОВНЕ: передаємо scanner і список деривативів
    }

    public void showHelp() {
        if (commands.isEmpty()) {
            System.out.println("Команди не зареєстровані.");
            return;
        }
        System.out.println("Доступні команди:");
        int i = 1;
        for (Map.Entry<String, Command> e : commands.entrySet()) {
            System.out.printf("%2d) %-12s — %s%n", i++, e.getKey(), oneLine(e.getValue().getDescription()));
        }
        System.out.println("Також доступні: help, exit");
    }

    public void exit() {
        running = false;
        System.out.println("Завершення роботи...");
    }

    /** Опціонально: зручно вибирати дериватив у команді.
     *  (Можеш використовувати або цей метод, або власний у командах) */
    public Derivative getDerivative() {
        if (derivatives.isEmpty()) {
            System.out.println("Список деривативів порожній.");
            return null;
        }
        System.out.println("Оберіть дериватив:");
        for (int i = 0; i < derivatives.size(); i++) {
            Derivative d = derivatives.get(i);
            String name = d.getName() != null ? d.getName() : "без назви";
            System.out.printf("%d) %s (%s)%n", i + 1, name, d.getId());
        }
        System.out.print("№: ");
        String s = in.hasNextLine() ? in.nextLine().trim() : "";
        int idx;
        try { idx = Integer.parseInt(s); } catch (NumberFormatException e) { idx = -1; }
        if (idx < 1 || idx > derivatives.size()) {
            System.out.println("Невірний вибір.");
            return null;
        }
        return derivatives.get(idx - 1);
    }

    // --- приватне ---
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
        registerCommand("q",      new Command() {
            @Override public void execute(Scanner in, List<Derivative> derivatives) { exit(); }
            @Override public String getDescription() { return "Вийти з програми (alias для exit)"; }
        });
    }

    private static String oneLine(String s) {
        if (s == null) return "";
        s = s.replace("\r", " ").replace("\n", " ");
        return s.length() > 80 ? s.substring(0, 77) + "..." : s;
    }
}
