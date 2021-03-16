package invoicing.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

import static invoicing.util.Alignment.*;
import static java.lang.Integer.min;

public class PrintUtil {
    List<ColumnDescriptor> columns = new ArrayList<>();
    String caption = "";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static class ColumnDescriptor{
        public final String property;
        public final String label;
        public final int width;
        public final Alignment alignment;
        public final int precision; //bank final

        public ColumnDescriptor(String property, String label, int width, Alignment alignment) {
            this.property = property;
            this.label = label;
            this.width = width;
            this.alignment = alignment;
            this.precision = 0;
        }
        public ColumnDescriptor(String property, String label, int width, Alignment alignment, int precision) {
            this.property = property;
            this.label = label;
            this.width = width;
            this.alignment = alignment;
            this.precision = precision;
        }
    }

    public static String formatTable(final List<ColumnDescriptor> columns, Collection<?> items, final String caption) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(caption).append("\n");
//        int width = 1;
//        for(ColumnDescriptor c : columns){
//            width += c.width + 3;
//        }
        int width = columns.stream().mapToInt(descr -> descr.width + 3).sum() + 1;

        // print heading with labels
        sb.append(repeat("-", width)).append("\n| ");
        for(ColumnDescriptor c : columns){
            appendStringAligned(sb, c.label, c.width, CENTER);
            sb.append(" | ");
        }
        sb.append("\n").append(repeat("-", width)).append("\n");
        // print table data
        for(Object item: items) {
            sb.append("| ");
            for(ColumnDescriptor c : columns){
                try {
                    Method accessor = item.getClass().getMethod(getAccessorMethodForProperty(c.property));
                    Object value = accessor.invoke(item); // invoke get method using reflection
                    if(value instanceof Float || value instanceof Double){
                        value = String.format("%" + c.width + "." + c.precision + "f", value);
                    } else if(value instanceof Date){
                        value = sdf.format(value);
                    }
                    appendStringAligned(sb, value.toString(), c.width, c.alignment);
                } catch (NoSuchMethodException| IllegalAccessException | InvocationTargetException | NullPointerException  e) {
//                    e.printStackTrace();
                    appendStringAligned(sb, "-", c.width, CENTER);
                }
                sb.append(" | ");
            }
            sb.append("\n");
        }
        sb.append(repeat("-", width)).append("\n");

        return sb.toString();
    }

    public static String repeat(String str, int number){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i  < number; i++){
            sb.append(str);
        }
        return sb.toString();
    }

    public static void appendStringAligned(StringBuilder sb, String str, int width, Alignment alignment){
        str = str.substring(0, min(width, str.length()));
        int spaces = width - str.length();
        switch (alignment) {
            case LEFT:
                sb.append(str).append(repeat(" ", spaces));
                break;
            case RIGHT:
                sb.append(repeat(" ", spaces)).append(str);
                break;
            case CENTER:
                sb.append(repeat(" ", spaces - spaces/2)).append(str).append(repeat(" ", spaces/2));
                break;
        }
    }

    public static String getAccessorMethodForProperty(String property) {
        StringBuilder sb = new StringBuilder(property);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.insert(0, "get");
        return sb.toString();
    }
}
