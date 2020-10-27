package course.java.invoicing.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface Repository<K, T extends Identifiable<K>> extends Serializable {
    Collection<T> findAll();
    List<T> findAllSorted(Comparator<T> comparator);
    List<T> findAllSorted(Comparator<T> comparator, boolean reverse);
    T findById(K id);
    T create(T entity);
    T update(T entity);
    T deleteById(K id);
    long count();
}
