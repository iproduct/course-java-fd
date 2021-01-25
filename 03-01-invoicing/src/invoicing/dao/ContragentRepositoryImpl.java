package invoicing.dao;

import invoicing.model.Contragent;

public class ContragentRepositoryImpl extends RepositoryMemoryImpl<Long, Contragent>
        implements ContragentRepository{
    public ContragentRepositoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
