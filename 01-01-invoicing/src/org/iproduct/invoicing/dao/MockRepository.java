package org.iproduct.invoicing.dao;

import java.util.*;

public class MockRepository<K, E extends Identifiable<K>> implements Repository<K, E>{
    private Map<K, E> items = new HashMap<>();
    private KeyGenerator<K> generator;

    // DI in constructor
    public MockRepository(KeyGenerator<K> generator) {
        this.generator = generator;
    }

    @Override
    public Collection<E> findAll() {
        return items.values();
    }

    @Override
    public E findById(K id) {
//        for(E item: items) { // O(N)
//            if(item.getId().equals(id)) {
//                return item;
//            }
//        }
//        Collections.binarySearch(items, new Identifiable(){ })
        return items.get(id);
    }

    @Override
    public E create(E item) {
        item.setId(generator.getNextId());
        return items.put(item.getId(), item);
    }

    @Override
    public E update(E item) {
        return items.put(item.getId(), item);
    }

    @Override
    public E deleteById(K id) {
        return items.remove(id);
    }

    @Override
    public long size() {
        return items.size();
    }
}
