package invoicing.util;

import invoicing.dao.Identifiable;

import javax.swing.*;
import java.util.List;

public class ReportGenerator {
    public static class ColumnDescriptor {
        public final String label;
        public final String property;
        public final int width;
        public final Alignment alignment;

        public ColumnDescriptor(String label, String property, int width, Alignment alignment) {
            this.label = label;
            this.property = property;
            this.width = width;
            this.alignment = alignment;
        }
    }
//    public static <C> String formatReport(List<ColumnDescriptor> descriptors,
//                              List<C> entities, Class<C> entityClass) {
//        StringBuilder sb = new StringBuilder();
//
//        entityClass.getDeclaredMethod()
//    }

    // Example: propertyName: "price" -> "getPrice"
    private static String getAccessorMethodName(String propertyName) {
//        StringBuilder sb = new StringBuilder(propertyName);
//        sb.replace(0,1, "" + Character.toUpperCase(sb.charAt(0)));
//        sb.insert(0, "get");
//        return sb.toString();
        return "get" + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
    }

    public static void main(String[] args) {
        System.out.println(getAccessorMethodName("price"));
        System.out.println(getAccessorMethodName("address"));
    }
}
