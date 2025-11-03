package com.org.insurance.ui.command;

import com.org.insurance.domain.Derivative;

import java.util.List;
import java.util.Scanner;

public class ShowDerivativesCommand implements Command {

    @Override
    public void execute(Scanner in, List<Derivative> derivatives) {
        if (derivatives == null || derivatives.isEmpty()) {
            System.out.println("Немає деривативів.");
            return;
        }

        System.out.println("Список деривативів:");
        for (int i = 0; i < derivatives.size(); i++) {
            Derivative d = derivatives.get(i);
            String name = d.getName() != null ? d.getName() : "без назви";
            int count = (d.getObligations() == null) ? 0 : d.getObligations().size();
            System.out.printf("%2d) %s (%s) — items: %d%n",
                    i + 1, name, d.getId(), count);
        }
    }

    @Override
    public String getDescription() {
        return "Показати список деривативів";
    }
}
