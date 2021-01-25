package invoicing.control;

import invoicing.dao.ContragentRepository;
import invoicing.dao.ContragentRepositoryImpl;
import invoicing.dao.LongKeyGenerator;
import invoicing.model.Contragent;

import java.util.List;

public class InvoiceController {
    private ContragentRepository contragentRepo = new ContragentRepositoryImpl(new LongKeyGenerator());
    private InvoiceController() {
    }
    // Factory method design pattern
    public static InvoiceController createInstance() {
        return new InvoiceController();
    }

    public String reportContragents() {
        StringBuilder sb = new StringBuilder();
        List<Contragent> contragents = contragentRepo.findAll();
        for(Contragent c: contragents){
            sb.append(c.format()).append("\n");
        }
        return sb.toString();
    }

    public Contragent addContragent(Contragent contragent){
        return contragentRepo.create(contragent);
    }

}
