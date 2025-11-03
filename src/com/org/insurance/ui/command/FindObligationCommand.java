package com.org.insurance.ui.command;

import java.util.UUID;

public class FindObligationCommand implements Command {
    private UUID derivativeId;
    private String name; // пошук за назвою

    public FindObligationCommand() { }

    public UUID getDerivativeId() { return derivativeId; }
    public void setDerivativeId(UUID derivativeId) { this.derivativeId = derivativeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override public void execute() { }
    @Override public String getDescription() { return "Знайти облігацію у деривативі"; }
}
