package course.java.invoicing.service;

import course.java.invoicing.dao.ContragentRepository;
import course.java.invoicing.exception.NonexistingProductException;
import course.java.invoicing.model.Contragent;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ContragentServiceImpl implements ContragentService {
    private final ContragentRepository contragentRepo;

    public ContragentServiceImpl(ContragentRepository contragentRepo){
        this.contragentRepo = contragentRepo;
    }

    @Override
    public Collection<Contragent> getAllContragents() {
        return contragentRepo.findAll();
    }

    @Override
    public List<Contragent> getContragentsSorted(Comparator<Contragent> comparator) {
        return contragentRepo.findAllSorted(comparator);
    }

    @Override
    public Contragent getContragentById(Long id) throws NonexistingProductException {
        Contragent found = contragentRepo.findById(id);
        if(found == null) {
            throw new NonexistingProductException(
                    String.format("Contragent with ID:%d does not exist", id));
        }
        return found;
    }

    @Override
    public Contragent addContragent(Contragent contragent) {
        return contragentRepo.create(contragent);
    }

    @Override
    public Contragent updateContragent(Contragent contragent) throws NonexistingProductException {
        Contragent updated = contragentRepo.update(contragent);
        if(updated == null) {
            throw new NonexistingProductException(
                    String.format("Contragent '%d: %s' does not exist",
                            contragent.getId(), contragent.getName()));
        }
        return updated;
    }

    @Override
    public Contragent deleteContragent(Long id) throws NonexistingProductException {
        Contragent deleted = contragentRepo.deleteById(id);
        if(deleted == null) {
            throw new NonexistingProductException(
                    String.format("Contragent with ID:%d does not exist", id));
        }
        return deleted;
    }

    @Override
    public long getCount() {
        return contragentRepo.count();
    }
}
