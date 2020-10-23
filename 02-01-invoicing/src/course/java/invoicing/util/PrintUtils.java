package course.java.invoicing.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public static String formatTable(List<ColumnDescriptor> columns, Collection<?> items) {
        StringBuilder sb = new StringBuilder();
        int width = columns.stream().mapToInt(c -> c.width).sum() + columns.size() + 1;
        sb.append("-".repeat(width)).append("\n");

        return sb.toString();
    }

    public static <E> String entitiesToString(Collection<E> entities) {
        return entities.stream()
                .map(entity -> String.format("%s%n", entity.toString()))
                .collect(Collectors.joining());
    }


}