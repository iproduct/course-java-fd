package course.java.invoicing.dao;

import java.io.Serializable;
import java.util.*;

public class MockRepository<K, T extends Identifiable<K>> implements Repository<K, T> {
    private Map<K, T> entities = new HashMap<>();
    private KeyGenerator<K> keyGenerator;

    // Constructor-based DI
    public MockRepository(KeyGenerator<K> keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @Override
    public Collection<T> findAll() {
        return entities.values();
    }

    @Override
    public List<T> findAllSorted(Comparator<T> comparator) {
        List<T> copy = new ArrayList<>(findAll());
        copy.sort(comparator);
        return copy;
    }

    @Override
    public List<T> findAllSorted(Comparator<T> comparator, boolean reverse) {
        if(reverse) {
            return findAllSorted(comparator.reversed());
        } else {
            return findAllSorted(comparator);
        }
    }

    @Override
    public T findById(K id) {
        return entities.get(id);
    }

    @Override
    public T create(T entity) {
        entity.setId(keyGenerator.getNextId());
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        T old = entities.replace(entity.getId(), entity);
        if(old == null) {
            return null;
        }
        return entity;
    }

    @Override
    public T deleteById(K id) {
        T old = entities.remove(id);
        if(old == null) {
            return null;
        }
        return old;
    }

    @Override
    public long count() {
        return entities.size();
    }
}
