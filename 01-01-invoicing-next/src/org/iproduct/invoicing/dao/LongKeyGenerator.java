package org.iproduct.invoicing.dao;

public class LongKeyGenerator implements KeyGenerator<Long> {
    private long nextId = 0L;

    @Override
    public Long getNextId() {
        return ++nextId;
    }
}
