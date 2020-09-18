package org.iproduct.invoicing.service;

import org.iproduct.invoicing.dao.Repository;
import org.iproduct.invoicing.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.exceptions.NonexistingEntityException;
import org.iproduct.invoicing.model.Contragent;

import java.util.ArrayList;
import java.util.List;

public class ContragentServiceImpl implements ContragentService {
    private Repository<Long, Contragent> contragentRepo;

    public ContragentServiceImpl(Repository<Long, Contragent> contragentRepo) {
        this.contragentRepo = contragentRepo;
    }

    @Override
    public List<Contragent> getAllContragents() {
        List<Contragent> results = new ArrayList<>(contragentRepo.findAll());
        return results;
    }

    @Override
    public Contragent getContragentById(Long id) {
        return contragentRepo.findById(id);
    }

    @Override
    public Contragent addContragent(Contragent contragent) throws EntityAlreadyExistsException {
        return contragentRepo.create(contragent);
    }

    @Override
    public Contragent updateContragent(Contragent contragent) throws NonexistingEntityException {
        return contragentRepo.update(contragent);
    }

    @Override
    public Contragent deleteContragentById(Long id) throws NonexistingEntityException {
        return contragentRepo.deleteById(id);
    }

    @Override
    public long size() {
        return contragentRepo.size();
    }
}
