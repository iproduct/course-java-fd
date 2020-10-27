package course.java.invoicing.util;

import course.java.invoicing.model.Role;
import course.java.invoicing.view.MenuItem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static course.java.invoicing.util.Alignment.CENTER;
import static java.lang.Math.min;
import static java.util.logging.Level.FINE;
import static java.util.logging.Level.WARNING;

public class PrintUtils {
    private static final Logger LOG = Logger.getLogger("c.j.i.u.PrintUtils");

    public static class ColumnDescriptor {
        public final String property;
        public final String label;
        public final int width;
        public final Alignment alignment;

        public ColumnDescriptor(String property, String label, int width, Alignment alignment) {
            this.property = property;
            this.label = label;
            this.width = width;
            this.alignment = alignment;
        }
    }

    public static String formatTable(List<ColumnDescriptor> columns, Collection<?> items) {
        StringBuilder sb = new StringBuilder();
        int width = columns.stream().mapToInt(c -> c.width).sum() + columns.size() + 1;
        sb.append("-".repeat(width)).append("\n|");
        columns.stream().forEach(cd -> {
            toStringAligned(sb, cd.width, CENTER, cd.label);
            sb.append("|");
        });
        sb.append("\n").append("-".repeat(width)).append("\n");
        for (Object item : items) {
            sb.append("|");
            for (int i = 0; i < columns.size(); i++) {
                ColumnDescriptor column = columns.get(i);
                StringBuilder getterName = new StringBuilder(column.property);
                getterName.setCharAt(0, Character.toUpperCase(getterName.charAt(0)));
                getterName.insert(0, "get");
                String strValue = "";
                try {
                    Method getter = item.getClass().getMethod(getterName.toString());
                    Object value = getter.invoke(item);
                    strValue = value != null ? value.toString() : "-";
                } catch (NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    LOG.log(FINE, String.format("Error getting property value for '%s'", column.property), e);
                    strValue = "-";

                }
                toStringAligned(sb, column.width, column.alignment, strValue);
                sb.append("|");
            }
            sb.append("\n");
        }
        sb.append("-".repeat(width)).append("\n");

        return sb.toString();
    }

    public static String formatMenu(Map<Integer, MenuItem> menuItems, Set<Role> roles) {
        StringBuilder sb = new StringBuilder();
        sb.append("-".repeat(50)).append("\n");
        for(Map.Entry<Integer, MenuItem> item : menuItems.entrySet()) {
            Set<Role> rolesAllowed = new HashSet<>(item.getValue().getRolesAlowed());
            rolesAllowed.retainAll(roles);
            if(rolesAllowed.size() > 0) {
                sb.append(item.getKey()).append(") ").append(item.getValue().getLabel()).append("\n");
            }
        }
        sb.append("-".repeat(50)).append("\n");
        return sb.toString();
    }

    private static void toStringAligned(StringBuilder sb, int width, Alignment alignment, String text) {
        text = text.substring(0, min(width, text.length()));
        int spaces = width - text.length();
        switch (alignment) {
            case LEFT:
                sb.append(text).append(" ".repeat(spaces));
                break;
            case RIGHT:
                sb.append(" ".repeat(spaces)).append(text);
                break;
            case CENTER:
                sb.append(" ".repeat(spaces - spaces / 2))
                        .append(text).append(" ".repeat(spaces / 2));
                break;
        }
    }

    public static <E> String entitiesToString(Collection<E> entities) {
        return entities.stream()
                .map(entity -> String.format("%s%n", entity.toString()))
                .collect(Collectors.joining());
    }


}
