package course.java.invoicing.util;

import java.util.Collection;
import java.util.stream.Collectors;

public class PrintUtils {
    public static <E> String entitiesToString(Collection<E> entities) {
        return entities.stream()
                .map(entity -> String.format("%s%n", entity.toString()))
                .collect(Collectors.joining());
    }
}
