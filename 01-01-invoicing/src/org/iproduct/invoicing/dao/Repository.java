package org.iproduct.invoicing.dao;

import org.iproduct.invoicing.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.exceptions.NonexistingEntityException;
import org.iproduct.invoicing.model.Product;

import java.util.Collection;
import java.util.List;

// Repository API
public interface Repository<K, E extends Identifiable<K>> {
    Collection<E> findAll();
    E findById(K id);
    E create(E item) throws EntityAlreadyExistsException;
    E update(E item) throws NonexistingEntityException;
    E deleteById(K id) throws NonexistingEntityException;
    long size();
}
