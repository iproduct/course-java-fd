package invoicing.dao.impl;

import invoicing.dao.IdGenerator;
import invoicing.dao.Repository;
import invoicing.exception.EntityNotFoundException;
import invoicing.model.Identifiable;

import java.util.*;

public class RepositoryMapImpl<K, V extends Identifiable<K>> implements Repository<K, V> {
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
        List<V> entityList = findAll();
        entityList.sort(comparator);
        return entityList;
    }

    @Override
    public Optional<V> findById(K id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public V create(V entity) {
        entity.setId(idGenerator.getNextId());
        return entities.put(entity.getId(), entity);
    }

    @Override
    public V update(V entity) throws EntityNotFoundException {
        V old = entities.replace(entity.getId(), entity);
        if (old == null) {
            throw new EntityNotFoundException(
                    String.format("%s with ID='%s' not found",
                            entity.getClass().getSimpleName(),
                            entity.getId().toString())
            );
        }
        return entity;
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
