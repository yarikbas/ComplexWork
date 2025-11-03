package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;

import java.util.List;
import java.util.Scanner;

public class ShowDerivativesCommand implements Command {
    @Override public void execute(Scanner in, List<Derivative> derivatives) { }
    @Override public String getDescription() { return "Показати список деривативів"; }
}
