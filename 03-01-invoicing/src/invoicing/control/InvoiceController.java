package invoicing.control;

import invoicing.dao.ContragentRepository;
import invoicing.dao.ContragentRepositoryImpl;
import invoicing.dao.LongKeyGenerator;
import invoicing.exception.InvalidClientDataException;
import invoicing.model.Contragent;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class InvoiceController {
    private static final Logger LOG = Logger.getLogger("i.c.InvoiceController");
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

    public Contragent addContragent(Contragent contragent) throws InvalidClientDataException{
        if(contragent.getName().length() < 2) {
            throw new InvalidClientDataException(
                String.format("Name should be at least 2 charactes long for contragent '%s'", contragent.getName()),
                contragent);
        }
        return contragentRepo.create(contragent);
    }

    public void save(String databaseFile) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(databaseFile));
        ) {
            out.writeObject(contragentRepo);
        }
    }

    public void load(String databaseFile) throws IOException {
        try(ObjectInputStream out = new ObjectInputStream(
                new FileInputStream(databaseFile));
        ) {
           contragentRepo = (ContragentRepository) out.readObject();
        } catch (ClassNotFoundException ex) {
            LOG.warning("The invoicing database file '" + databaseFile + "' has invalid data serialization format");
        }
    }

}
