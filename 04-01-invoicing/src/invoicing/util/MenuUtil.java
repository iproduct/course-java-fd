package invoicing.util;

import invoicing.model.Role;
import invoicing.view.MenuItem;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MenuUtil {
    public static void showMenu(Map<Integer, MenuItem> menuItems) {
        boolean exit = false;
        do {
            System.out.println();
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
