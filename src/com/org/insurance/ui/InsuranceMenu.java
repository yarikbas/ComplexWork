package com.org.insurance.ui;

import com.org.insurance.domain.Derivative;
import com.org.insurance.ui.command.Command;

import java.util.*;

public class InsuranceMenu {
    private final List<Derivative> derivatives = new ArrayList<Derivative>();
    private final Map<String, Command> commands = new LinkedHashMap<String, Command>();
    private final Scanner in = new Scanner(System.in).useLocale(Locale.US);
    private boolean running = false;
    private Derivative selected;

    public void register(String name, Command cmd) { commands.put(name, cmd); }

    public void run() {
        running = true;
        System.out.println("Insurance CLI (type 'help' or 'exit')");
        while (running) {
            System.out.print("> ");
            String cmd = in.nextLine().trim();
            if ("exit".equalsIgnoreCase(cmd)) { exit(); continue; }
            if ("help".equalsIgnoreCase(cmd)) { showHelp(); continue; }
            Command c = commands.get(cmd);
            if (c == null) { System.out.println("Unknown command: " + cmd); continue; }
            try { c.execute(in); } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        }
    }

    public void showHelp() {
        System.out.println("Commands:");
        for (Map.Entry<String, Command> e : commands.entrySet()) {
            System.out.println(" - " + e.getKey() + " : " + e.getValue().getDescription());
        }
    }
    public void exit() { running = false; }

    // helpers
    public List<Derivative> getDerivatives() { return derivatives; }
    public Derivative getSelected() { return selected; }
    public void setSelected(Derivative d) { this.selected = d; }
    public void addDerivative(Derivative d) { if (d != null) derivatives.add(d); }
    public Scanner getScanner() { return in; }
}
