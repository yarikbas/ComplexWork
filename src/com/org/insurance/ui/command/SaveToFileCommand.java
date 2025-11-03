package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;
import com.org.insurance.io.FileManager;
import com.org.insurance.ui.InsuranceMenu;
import java.util.List;

public class SaveToFileCommand implements Command {
    @Override
    public String getDescription() {
        return "Save derivatives to file";
    }

    @Override
    public void execute(List<Derivative> derivatives) {
        System.out.print("Enter file path: ");
        String path = InsuranceMenu.scanner.nextLine();

        FileManager fileManager = new FileManager();
        fileManager.saveToFile(derivatives, path);
    }
}