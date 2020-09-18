package org.iproduct.invoicing;

import org.iproduct.invoicing.dao.KeyGenerator;
import org.iproduct.invoicing.dao.LongKeyGenerator;
import org.iproduct.invoicing.dao.MockRepository;
import org.iproduct.invoicing.dao.Repository;
import org.iproduct.invoicing.exceptions.ActionUnsuccessfulException;
import org.iproduct.invoicing.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.model.Client;
import org.iproduct.invoicing.model.Contragent;
import org.iproduct.invoicing.model.Issuer;
import org.iproduct.invoicing.model.Product;
import org.iproduct.invoicing.service.ContragentService;
import org.iproduct.invoicing.service.ContragentServiceImpl;
import org.iproduct.invoicing.service.ProductService;
import org.iproduct.invoicing.service.ProductServiceImpl;
import org.iproduct.invoicing.view.MenuItem;
import org.iproduct.invoicing.view.commands.AddProductCommand;
import org.iproduct.invoicing.view.commands.Command;

import java.util.*;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static org.iproduct.invoicing.view.Alignment.*;
import static org.iproduct.invoicing.view.InputUtils.aswerYesNoQuestion;
import static org.iproduct.invoicing.view.InputUtils.inputInt;
import static org.iproduct.invoicing.view.MenuItem.*;
import static org.iproduct.invoicing.view.PrintUtils.*;

public class InvoiceRegister {
    private static final Logger LOG = Logger.getLogger("o.i.i.s.InvoiceRegister");
    private static final List<ColumnDescriptor> PRODUCT_DESCRIPTORS = List.of(
            new ColumnDescriptor("id", "ID", 4, RIGHT),
            new ColumnDescriptor("code", "Code", 7, CENTER),
            new ColumnDescriptor("name", "Name", 30, LEFT),
            new ColumnDescriptor("price", "Price", 10, RIGHT),
            new ColumnDescriptor("unit", "Unit", 4, CENTER)
    );

    private Repository<Long, Product> productRepo;
    private ProductService productService;
    private Repository<Long, Contragent> contragentRepo;
    private ContragentService contragentService;
    private Map<MenuItem, Command> commands = new HashMap<>();
    private List<MenuItem> mainMenu = new ArrayList<>();


    public void init() {
        // Initialize products
        KeyGenerator<Long> longKeyGenerator = new LongKeyGenerator();
        productRepo = new MockRepository<>(longKeyGenerator);
        productService = new ProductServiceImpl(productRepo);
        List<Product> sampleProducts = List.of(
                new Product("BK002", "UML Distilled", 28.7),
                new Product("BK001", "Thinking in Java", 35.5),
                new Product("BK003", "Head-first Java", 32.7),
                new Product("BK004", "Effective Java", 45.5)
        );
        for (Product p : sampleProducts) {
            try {
                productService.addProduct(p);
            } catch (EntityAlreadyExistsException e) {
                LOG.log(SEVERE, "Error creating product:", e);
            }
        }

        // Initialize contragents
        List<Contragent> contragents = Arrays.asList(new Contragent[]{
                new Issuer(123456789L, "IT Bookstore Ltd.", "Sofia, Ivan Asen 25A",
                        "BGUNCR1234567890", "BGUNCR",
                        "+(359) 2 896123", "BG123456789"),
                new Issuer(567889432L, "Best Software AD", "Sofia, 1000",
                        "BGFIB123456ASD7890", "BGFIB",
                        "+(359) 2 567789", "BG567889432"),
                new Client(923456789L, "ABC Ltd.", "Sofia, Ivan Asen 25A",
                        "(+359) 2 896123", "BG123456789", "abc@abv.bg"),
                new Client(867889432L, "Dimitar Petrov", "Provdiv, ul. Centralna, 56",
                        "(+359) 32 34534", null, "dimitar@gmail.com", true),
        });
        contragentRepo = new MockRepository<>();
        contragentService = new ContragentServiceImpl(contragentRepo);

        contragents.forEach(issuer -> {
            try {
                contragentService.addContragent(issuer);
            } catch (EntityAlreadyExistsException e) {
                LOG.log(SEVERE, "Error creating contragent:", e);
            }
        });

        // Initialize menus
        commands.put(ADD_PRODUCT, new AddProductCommand(productService));
        commands.put(PRINT_PRODUCTS, () -> formatTable(PRODUCT_DESCRIPTORS, productService.getAllProducts()));
        commands.put(EXIT, () -> {
            if (aswerYesNoQuestion("Are you shure you want to exit")) {
                System.exit(0);
                return "Good bye!";
            } else {
                return "";
            }
        });

        mainMenu.add(ADD_PRODUCT);
        mainMenu.add(PRINT_PRODUCTS);
        mainMenu.add(EXIT);
    }

    public void showMainMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println(formatMenu(mainMenu));
            choice = inputInt("Choose an option", 1, mainMenu.size());
            MenuItem chosenItem = mainMenu.get(choice - 1);
            try {
                System.out.println(commands.get(chosenItem).action());
            } catch (ActionUnsuccessfulException e) {
                System.out.printf("Error: %s", e.getMessage());
            }
        } while(true);
    }

    public Repository<Long, Product> getProductRepo() {
        return productRepo;
    }

    public ProductService getProductService() {
        return productService;
    }

    public Repository<Long, Contragent> getContragentRepo() {
        return contragentRepo;
    }

    public ContragentService getContragentService() {
        return contragentService;
    }

    public static void main(String[] args) {
        InvoiceRegister register = new InvoiceRegister();
        register.init();
        register.showMainMenu();
    }
}
