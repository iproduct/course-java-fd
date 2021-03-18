package invoicing.dao;

import invoicing.exception.EntityNotFoundException;
import invoicing.model.Identifiable;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface Repository<K, V extends Identifiable<K>> {
    List<V> findAll();
    List<V> findAllSorted(Comparator<V> comparator);
    Optional<V> findById(K id);
    V create(V entity);
    V update(V entity) throws EntityNotFoundException;
    Optional<V> deleteById(K id);
    long count();
}
