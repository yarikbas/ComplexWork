package com.org.insurance.ui.command;

import java.util.UUID;

public class SortByRiskCommand implements Command {
    private UUID derivativeId;
    private boolean ascending = true;

    public SortByRiskCommand() { }

    public UUID getDerivativeId() { return derivativeId; }
    public void setDerivativeId(UUID derivativeId) { this.derivativeId = derivativeId; }

    public boolean isAscending() { return ascending; }
    public void setAscending(boolean ascending) { this.ascending = ascending; }

    @Override public void execute() { }
    @Override public String getDescription() { return "Відсортувати облігації за ризиком"; }
}
