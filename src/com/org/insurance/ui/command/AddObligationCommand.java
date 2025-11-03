package com.org.insurance.ui.command;

import com.org.insurance.domain.*;
import java.util.UUID;

public class AddObligationCommand implements Command {
    private UUID derivativeId;
    private Obligation obligation;

    public AddObligationCommand() { }

    public UUID getDerivativeId() { return derivativeId; }
    public void setDerivativeId(UUID derivativeId) { this.derivativeId = derivativeId; }

    public Obligation getObligation() { return obligation; }
    public void setObligation(Obligation obligation) { this.obligation = obligation; }

    @Override public void execute() { }
    @Override public String getDescription() { return "Додати облігацію до деривативу"; }
}
