package org.iproduct.invoicing.dao;

import org.iproduct.invoicing.model.Contragent;

import java.util.Collection;
import java.util.List;

public class MockContragentRepository extends MockRepository<Long, Contragent>
        implements ContragentRepository {
    @Override
    public Contragent findByName(String name) {
        Collection<Contragent> contragents = findAll();
        for(Contragent c: contragents) {
            if(c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
}
