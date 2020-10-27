package course.java.invoicing.service;

import course.java.invoicing.dao.ContragentRepository;
import course.java.invoicing.dao.ProductRepository;
import course.java.invoicing.exception.NonexistingEntityException;
import course.java.invoicing.model.Contragent;
import course.java.invoicing.model.Product;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ContragentService {
    Collection<Contragent> getAllContragents();
    List<Contragent> getContragentsSorted(Comparator<Contragent> comparator);
    Contragent getContragentById(Long id) throws NonexistingEntityException;
    Contragent addContragent(Contragent contragent);
    Contragent updateContragent(Contragent contragent) throws NonexistingEntityException;
    Contragent deleteContragent(Long id) throws NonexistingEntityException;
    long getCount();
    ContragentRepository getContragentRepository();
    void setContragentRepository(ContragentRepository repo);

}
