package course.java.invoicing.service;

import course.java.invoicing.exception.NonexistingProductException;
import course.java.invoicing.model.Contragent;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ContragentService {
    Collection<Contragent> getAllContragents();
    List<Contragent> getContragentsSorted(Comparator<Contragent> comparator);
    Contragent getContragentById(Long id) throws NonexistingProductException;
    Contragent addContragent(Contragent contragent);
    Contragent updateContragent(Contragent contragent) throws NonexistingProductException;
    Contragent deleteContragent(Long id) throws NonexistingProductException;
    long getCount();
}
