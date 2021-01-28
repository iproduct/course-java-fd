package invoicing.dao;

import java.io.Serializable;

public class LongKeyGenerator implements KeyGenerator<Long>, Serializable {
    static final long serialVersionUID = 1L;
    private long nextId = 0;
    @Override
    public Long getNextId() {
        return ++nextId;
    }
}
