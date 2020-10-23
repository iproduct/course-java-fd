package course.java.invoicing.service;

import course.java.invoicing.dao.ContragentRepository;
import course.java.invoicing.model.Contragent;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ContragentServiceImpl implements ContragentService {
    private ContragentRepository contragentRepo;

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
    public Contragent getContragentById(Long id) {
        return contragentRepo.findById(id);
    }

    @Override
    public Contragent addContragent(Contragent contragent) {
        return contragentRepo.create(contragent);
    }

    @Override
    public Contragent updateContragent(Contragent contragent) {
        return contragentRepo.update(contragent);
    }

    @Override
    public Contragent deleteContragent(Long id) {
        return contragentRepo.deleteById(id);
    }

    @Override
    public long getCount() {
        return contragentRepo.count();
    }
}
