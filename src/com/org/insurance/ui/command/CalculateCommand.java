package com.org.insurance.ui.command;

import java.util.UUID;

public class CalculateCommand implements Command {
    private UUID derivativeId;

    public CalculateCommand() { }

    public UUID getDerivativeId() { return derivativeId; }
    public void setDerivativeId(UUID derivativeId) { this.derivativeId = derivativeId; }

    @Override public void execute() { }
    @Override public String getDescription() { return "Розрахувати показники деривативу"; }
}
