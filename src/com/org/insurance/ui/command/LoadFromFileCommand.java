package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.io.FileManager;
import com.org.insurance.ui.InsuranceMenu;
import java.util.List;

public class LoadFromFileCommand implements Command {
    @Override
    public String getDescription() {
        return "Load derivatives from file";
    }

    @Override
    public void execute(List<Derivative> derivatives) {
        System.out.print("Enter file path: ");
        String path = InsuranceMenu.scanner.nextLine();

        FileManager fileManager = new FileManager();
        List<Derivative> loaded = fileManager.loadFromFile(path);

        if (loaded != null) {
            derivatives.clear();
            derivatives.addAll(loaded);
            System.out.println("Loaded " + loaded.size() + " derivatives");
        }
    }
}