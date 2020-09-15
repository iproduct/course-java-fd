package org.iproduct.invoicing.view;

import org.iproduct.invoicing.dao.Identifiable;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.iproduct.invoicing.view.Alignment.CENTER;

public class PrintUtils {

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

    public static String printTable(List<ColumnDescriptor> columns, List<?> items) {
        StringBuilder sb = new StringBuilder();
        int width = columns.stream().mapToInt(column -> column.width).sum() + columns.size() + 1;
        sb.append("-".repeat(width)).append("\n|");
        for (ColumnDescriptor c : columns) {
            appendAligned(sb, c.width, CENTER, c.label);
            sb.append("|");
        }
        sb.append("\n").append("-".repeat(width)).append("\n|");

        // build accessor method name
        for (int line = 0; line < items.size(); line++) {
            for (int i = 0; i < columns.size(); i++) {
                ColumnDescriptor column = columns.get(i);
                StringBuilder propName = new StringBuilder(column.property);
                propName.setCharAt(0, Character.toTitleCase(propName.charAt(0)));
                propName.insert(0, "get");
                // Call accessor method by reflection
                try {
                    Method accessor = items.get(line).getClass().getMethod(propName.toString());
                    Object result = accessor.invoke(items.get(line));
                    if(result == null) {
                        result = "null";
                    } else if(result instanceof Double) {
                        result = String.format("%" + column.width + ".2f", result);
                    }
                    appendAligned(sb, column.width, column.alignment, result.toString());
                } catch (NoSuchMethodException e) {
                    appendAligned(sb, column.width, CENTER, "-");
//                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                sb.append("|");
            }
            sb.append("\n").append((line < items.size() - 1) ? "|" : "");
        }
        sb.append("-".repeat(width)).append("\n");
        return sb.toString();
    }

    private static void appendAligned(StringBuilder sb, int width, Alignment alignment, String label) {
        int spacesBefore = 0, spacesAfter = 0;
        if (label.length() > width) {
            label = label.substring(0, width);
        }
        switch (alignment) {
            case LEFT:
                spacesAfter = width - label.length();
                break;
            case RIGHT:
                spacesBefore = width - label.length();
                break;
            case CENTER:
                spacesBefore = (width - label.length()) / 2;
                spacesAfter = width - label.length() - spacesBefore;
        }
        sb.append(" ".repeat(spacesBefore)).append(label).append(" ".repeat(spacesAfter));
    }


}
