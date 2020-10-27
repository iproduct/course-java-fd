package course.java.invoicing.dao;

import java.io.Serializable;

@FunctionalInterface //SAM
public interface KeyGenerator<K> extends Serializable {
    K getNextId();
}
