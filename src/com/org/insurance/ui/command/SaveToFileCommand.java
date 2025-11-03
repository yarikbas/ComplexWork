package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.io.FileManager;
import com.org.insurance.ui.InsuranceMenu;
import com.org.insurance.ui.Inputs;

import java.util.Scanner;

public class SaveToFileCommand implements Command {
    private final InsuranceMenu menu;
    private final FileManager fm = new FileManager();
    public SaveToFileCommand(InsuranceMenu menu) { this.menu = menu; }

    @Override public String getDescription() { return "Save selected derivative to file"; }

    @Override
    public void execute(Scanner sc) {
        Derivative d = menu.getSelected();
        if (d == null) { System.out.println("No selected derivative."); return; }
        String file = Inputs.nextLine(sc, "Filename: ");
        fm.saveDerivative(d, file.trim());
        System.out.println("Saved to " + file);
    }
}
