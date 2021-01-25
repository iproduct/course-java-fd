package invoicing.control;

import invoicing.dao.ContragentRepository;
import invoicing.dao.ContragentRepositoryImpl;
import invoicing.dao.LongKeyGenerator;
import invoicing.model.Contragent;

import java.util.List;

public class InvoiceController {
    private ContragentRepository contragentRepo = new ContragentRepositoryImpl(new LongKeyGenerator());
    private static InvoiceController theInstance;
    private InvoiceController() {
    }
    // Singleton design pattern
    public static InvoiceController getInstance() {
        if(theInstance == null) {
            theInstance = new InvoiceController();
        }
        return theInstance;
    }

    public String reportContragents() {
        StringBuilder sb = new StringBuilder();
        List<Contragent> contragents = contragentRepo.findAll();
        for(Contragent c: contragents){
            sb.append(c.format()).append(" ")
                .append(String.format("%-10.10s", c.getClass().getSimpleName()))
                .append(" |\n");
        }
        return sb.toString();
    }

    public Contragent addContragent(Contragent contragent){
        return contragentRepo.create(contragent);
    }

}
