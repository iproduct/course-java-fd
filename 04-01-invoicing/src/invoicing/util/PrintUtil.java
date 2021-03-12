package invoicing.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
        sb.append("\n").append(repeat("-", width));


        return sb.toString();
    }

    public static String repeat(String str, int number){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i  < number; i++){
            sb.append(str);
        }
        return sb.toString();
    }
}
