package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.io.FileManager;
import com.org.insurance.ui.InsuranceMenu;
import com.org.insurance.ui.Inputs;

import java.util.Scanner;

public class LoadFromFileCommand implements Command {
    private final InsuranceMenu menu;
    private final FileManager fm = new FileManager();
    public LoadFromFileCommand(InsuranceMenu menu) { this.menu = menu; }

    @Override public String getDescription() { return "Load derivative from file and select it"; }

    @Override
    public void execute(Scanner sc) {
        String file = Inputs.nextLine(sc, "Filename: ");
        Derivative d = fm.loadDerivative(file.trim());
        menu.addDerivative(d);
        menu.setSelected(d);
        System.out.println("Loaded & selected: " + d.getName());
    }
}
