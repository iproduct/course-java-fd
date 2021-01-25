package invoicing.dao;

public class LongKeyGenerator implements KeyGenerator<Long>{
    private long nextId = 0;
    @Override
    public Long getNextId() {
        return ++nextId;
    }
}
