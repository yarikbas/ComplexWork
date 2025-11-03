package com.org.insurance.ui.command;

public class CreateDerivativeCommand implements Command {
    private String name;

    public CreateDerivativeCommand() { }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override public void execute() { }
    @Override public String getDescription() { return "Створити дериватив"; }
}
