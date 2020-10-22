package course.java.invoicing.dao;

import java.util.concurrent.atomic.AtomicLong;

public class LongKeyGenerator implements KeyGenerator<Long>{
    private AtomicLong nextId;

    public LongKeyGenerator(){
        nextId = new AtomicLong(0L);
    }

    public LongKeyGenerator(Long start){
        nextId = new AtomicLong(start);
    }

    @Override
    public Long getNextId() {
        return nextId.incrementAndGet();
    }
}
