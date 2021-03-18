package invoicing.util;

import invoicing.model.Role;
import invoicing.view.Command;
import invoicing.view.MenuItem;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static invoicing.model.Role.ADMIN;
import static invoicing.util.FieldType.INTEGER;

public class MenuUtil {
    public static void showMenu(Map<Integer, MenuItem> menuItems, Map<MenuItem, Command> commands) {
        boolean exit = false;
        int choice;
        FieldConfig intFieldConfig = new FieldConfig(null, "Choose an option", INTEGER, 2);
        do {
            System.out.println(formatMenu(menuItems, ADMIN));
            choice = InputUtil.inputLong(intFieldConfig).intValue();
            MenuItem chosenItem = menuItems.get(choice);
            if(chosenItem != null) {
                Command chosenCommand = commands.get(chosenItem);
                System.out.println();
                System.out.println(chosenCommand.execute());
            } else {
                System.out.println("Invalid chice. Try again");
            }
        } while (!exit);
    }

    public static String formatMenu(Map<Integer, MenuItem> menuItems, Role activeRole) {
        StringBuilder sb = new StringBuilder();
        sb.append(PrintUtil.repeat("-", 50)).append("\n");
        for(Map.Entry<Integer, MenuItem> item : menuItems.entrySet()) {
            if(item.getValue().getRolesAlowed().contains(activeRole)) {
                sb.append(item.getKey()).append(") ").append(item.getValue().getLabel()).append("\n");
            }
        }
        sb.append(PrintUtil.repeat("-", 50)).append("\n");
        return sb.toString();
    }
}
