package invoicing.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static invoicing.util.Alignment.*;
import static java.lang.Integer.min;

public class PrintUtil {
    public static class ColumnDescriptor{
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
        int width = 1;
        for(ColumnDescriptor c : columns){
            width += c.width + 1;
        }
        sb.append("\n").append(repeat("-", width)).append("\n|");
        for(ColumnDescriptor c : columns){
            toStringAligned(sb, c.label, c.width, CENTER);
            sb.append("|");
        }
        sb.append("\n").append(repeat("-", width)).append("\n|");


        return sb.toString();
    }

    public static String repeat(String str, int number){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i  < number; i++){
            sb.append(str);
        }
        return sb.toString();
    }

    public static void toStringAligned(StringBuilder sb, String str, int width, Alignment alignment){
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
}
