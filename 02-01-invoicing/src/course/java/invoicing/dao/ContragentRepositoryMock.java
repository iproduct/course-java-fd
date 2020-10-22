package course.java.invoicing.dao;

import course.java.invoicing.model.Contragent;

public class ContragentRepositoryMock extends MockRepository<Long, Contragent> implements ContragentRepository {
    public ContragentRepositoryMock(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
