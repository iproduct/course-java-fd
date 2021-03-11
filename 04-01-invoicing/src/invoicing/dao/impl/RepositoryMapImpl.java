package invoicing.dao.impl;

import invoicing.dao.IdGenerator;
import invoicing.dao.Repository;
import invoicing.model.Identifiable;

import java.util.*;

public class RepositoryMapImpl<K, V extends Identifiable<K>> implements Repository<K,V> {
    private static long nextId = 0L;
    private Map<K, V> entities = new HashMap<>();
    private IdGenerator<K> idGenerator;

    public RepositoryMapImpl(IdGenerator<K> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public List<V> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public List<V> findAllSorted(Comparator comparator) {
        List<V> productsList = findAll();
        productsList.sort(comparator);
        return productsList;
    }

    @Override
    public Optional<V> findById(K id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public V create(V product) {
        product.setId(idGenerator.getNextId());
        return entities.put(product.getId(), product);
    }

    @Override
    public V update(V product) {
        V old = entities.replace(product.getId(), product);
        if(old == null) {
            // TODO throw exception
        }
        return product;
    }

    @Override
    public Optional<V> deleteById(K id) {
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public long count() {
        return entities.size();
    }
}
