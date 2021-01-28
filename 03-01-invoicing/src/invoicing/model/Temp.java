package invoicing.model;

import invoicing.dao.ContragentRepository;
import invoicing.dao.ContragentRepositoryImpl;
import invoicing.dao.KeyGenerator;
import invoicing.dao.LongKeyGenerator;

import java.util.List;

public class Temp {
    public static void main(String[] args) {
        // Formatting demo + visibility
        Contragent c2 = new Contragent("Best Widgets Ltd.", "Plovdiv, 25A",
                "111111111", "(+359)32 1234566");
        Contragent c3 = new Contragent("John Smith", "Plovdiv, 25A",
                "1234567890", "john@gmail.com");
        KeyGenerator<Long> keyGenerator = new LongKeyGenerator();
        ContragentRepository contragentRepo = new ContragentRepositoryImpl(keyGenerator);
        Contragent result = contragentRepo.create(c2);
        contragentRepo.create(c3);
        List<Contragent> contragents = contragentRepo.findAll();
        for(Contragent c : contragents) {
            System.out.println(c.format());
        }
    }
}
