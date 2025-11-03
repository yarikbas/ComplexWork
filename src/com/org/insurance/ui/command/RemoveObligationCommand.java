package com.org.insurance.ui.command;

import java.util.UUID;

public class RemoveObligationCommand implements Command {
    private UUID derivativeId;
    private UUID obligationId;

    public RemoveObligationCommand() { }

    public UUID getDerivativeId() { return derivativeId; }
    public void setDerivativeId(UUID derivativeId) { this.derivativeId = derivativeId; }

    public UUID getObligationId() { return obligationId; }
    public void setObligationId(UUID obligationId) { this.obligationId = obligationId; }

    @Override public void execute() { }
    @Override public String getDescription() { return "Видалити облігацію з деривативу"; }
}
