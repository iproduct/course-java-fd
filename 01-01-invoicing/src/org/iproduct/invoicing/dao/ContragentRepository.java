package org.iproduct.invoicing.dao;

import org.iproduct.invoicing.model.Contragent;

public interface ContragentRepository extends  Repository<Long, Contragent> {
    Contragent findByName(String name);
}
