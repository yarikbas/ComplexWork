package com.org.insurance;

import com.org.insurance.ui.InsuranceMenu;
import com.org.insurance.ui.command.*;

public class Main {
    public static void main(String[] args) {
        InsuranceMenu menu = new InsuranceMenu();

        menu.register("new",  new CreateDerivativeCommand(menu));
        menu.register("list", new ShowDerivativesCommand(menu));
        menu.register("del",  new DeleteDerivativeCommand(menu));

        menu.register("add",  new AddObligationCommand(menu));
        menu.register("rm",   new RemoveObligationCommand(menu));
        menu.register("sort", new SortByRiskCommand(menu));
        menu.register("find", new FindObligationCommand(menu));
        menu.register("calc", new CalculateCommand(menu));

        menu.register("save", new SaveToFileCommand(menu));
        menu.register("load", new LoadFromFileCommand(menu));

        menu.run();
    }
}
