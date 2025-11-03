package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import java.util.List;

public interface Command {
    String getDescription();
    void execute(List<Derivative> derivatives);
}