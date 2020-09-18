package org.iproduct.invoicing.service;

import org.iproduct.invoicing.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.exceptions.NonexistingEntityException;
import org.iproduct.invoicing.model.Contragent;

import java.util.List;

public interface ContragentService {
    List<Contragent> getAllContragents();
    Contragent getContragentById(Long id);
    Contragent addContragent(Contragent contragent) throws EntityAlreadyExistsException;
    Contragent updateContragent(Contragent contragent) throws NonexistingEntityException;
    Contragent deleteContragentById(Long id) throws NonexistingEntityException;
    long size();
}
