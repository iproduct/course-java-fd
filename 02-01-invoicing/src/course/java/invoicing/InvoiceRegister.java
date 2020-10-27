package course.java.invoicing;

import course.java.invoicing.dao.*;
import course.java.invoicing.exception.ActionUnsuccessfulException;
import course.java.invoicing.exception.NonexistingEntityException;
import course.java.invoicing.model.*;
import course.java.invoicing.service.*;
import course.java.invoicing.util.*;
import course.java.invoicing.view.Command;
import course.java.invoicing.view.MenuItem;
import course.java.invoicing.view.command.AppProductCommand;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static course.java.invoicing.model.Unit.M;
import static course.java.invoicing.util.Alignment.*;
import static course.java.invoicing.util.FieldType.*;
import static course.java.invoicing.util.PrintUtils.*;
import static course.java.invoicing.view.MenuItem.*;

public class InvoiceRegister {
    private static Logger LOG = Logger.getLogger("c.j.i.InvoiceRegister");
    public static final String DATABASE_FILENAME = "./invoicing.db";
    //    static {
//        LOG.setLevel(Level.SEVERE);
//    }
    // or -Djava.util.logging.config.file=logging.properties
    public static final List<ColumnDescriptor> productColumns = Arrays.asList(new ColumnDescriptor[]{
            new ColumnDescriptor("id", "ID", 5, RIGHT),
            new ColumnDescriptor("code", "Code", 7, CENTER),
            new ColumnDescriptor("name", "Name", 30, LEFT),
            new ColumnDescriptor("price", "Price", 10, RIGHT),
            new ColumnDescriptor("unit", "Unit", 4, CENTER)
    });
    public static final List<ColumnDescriptor> contragentColumns = Arrays.asList(new ColumnDescriptor[]{
            new ColumnDescriptor("id", "ID", 10, RIGHT),
            new ColumnDescriptor("name", "Name", 30, LEFT),
            new ColumnDescriptor("address", "Address", 30, RIGHT),
            new ColumnDescriptor("telephone", "Phone", 18, CENTER),
            new ColumnDescriptor("vatNumber", "VAT #", 12, CENTER),
            new ColumnDescriptor("email", "Email", 30, CENTER),
    });

    private ProductService productService;
    private ContragentService contragentService;
    private UserService userService;
    private Map<MenuItem, Command> commands = new HashMap<>();

    private User loggedUser;

    public InvoiceRegister() {
        KeyGenerator<Long> productIdGen = new LongKeyGenerator();
        ProductRepository productRepo = new ProductRepositoryMock(productIdGen);
        productService = new ProductServiceImpl(productRepo);
        KeyGenerator<Long> contragentIdGen = new LongKeyGenerator();
        ContragentRepository contragentRepo = new ContragentRepositoryMock(contragentIdGen);
        contragentService = new ContragentServiceImpl(contragentRepo);
        KeyGenerator<Long> userIdGen = new LongKeyGenerator();
        UserRepository userRepo = new UserRepositoryMock(userIdGen);
        userService = new UserServiceImpl(userRepo);
    }

    protected User getLoggedUser() {
        return loggedUser;
    }

    protected void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void init() {
        //init contragents
        contragentService.addContragent(
                new Supplier(123456789L, "Best Widgets", "Sofia 1000", "BG123456789",
                        "RZBB123A1234566677", "RZBB"));
        contragentService.addContragent(
                new Client(9545678901L, "Ivan Petrov", "Plovdiv", "john@abv.bg", true));

        // init products
        Arrays.stream(new Product[]{
                new Product("BK001", "Thinking in Java", 35.5),
                new Product("BK002", "UML Distilled", 28.7),
                new Product("CB002", "Network cable Cat.6", 2.25, M),
                new Product("BK001", "Thinking in Java", 35.5)})
                .forEach(productService::addProduct);

        // init users
        Arrays.stream(new User[]{
                new User("Default", "Admin", "admin", "admin",
                        new HashSet<>(Arrays.asList(Role.ADMIN)), true),
                new User("Default", "User", "user", "user",
                        new HashSet<>(Arrays.asList(Role.USER)), true)
        }).forEach(userService::addUser);

        try {
            loggedUser = userService.getUserByUsername("admin");
        } catch (NonexistingEntityException e) {
            LOG.warning("Default user not found.");
        }

        // init menu commands

        commands.put(ADD_PRODUCT, new AppProductCommand(productService));
        commands.put(PRINT_PRODUCTS, () -> {
            Collection<Product> products = productService.getAllProducts();
            return formatTable(productColumns, products)
                + String.format("Total product count: %s%n", products.size());
        });
        commands.put(WRITE_TO_FILE, () -> {
            File dbFile = new File(DATABASE_FILENAME);
            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dbFile));
                outputStream.writeObject(new AllRepos(productService.getProductRepository(),
                        contragentService.getContragentRepository(), userService.getUserRepository()));
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Can not write to file: " + dbFile.getAbsolutePath(), e);
            }
            return String.format("Data written successfully to file: %s", dbFile.getAbsolutePath() );
        });
        commands.put(READ_FROM_FILE, () -> {
            File dbFile = new File(DATABASE_FILENAME);
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(dbFile));
                Object result = inputStream.readObject();
                if(result instanceof AllRepos) {
                    AllRepos allRepos = (AllRepos) result;
                    productService.setProductRepository(allRepos.getProductRepository());
                    contragentService.setContragentRepository(allRepos.getContragentRepository());
                    userService.setUserRepository(allRepos.getUserRepository());
                }
            } catch (ClassNotFoundException | IOException e) {
                LOG.log(Level.SEVERE, "Can not write to file: " + dbFile.getAbsolutePath(), e);
            }
            return String.format("Data read successfully from file: %s", dbFile.getAbsolutePath() );
        });
        commands.put(EXIT, () -> {
            System.exit(0);
            return "Good Bye.";
        });
        Map<Integer, MenuItem> mainMenu = new LinkedHashMap<>();
        int index = 0;
        MenuItem[] items = MenuItem.values();
        for(MenuItem item : Arrays.copyOf(items, items.length - 1)) {
            mainMenu.put(++index, item);
        }
        showMenu(mainMenu);

    }

    public void showMenu(Map<Integer, MenuItem> menuItems) {
        int choice;
        FieldConfig intFieldConfig = new FieldConfig(null, "Choose an option", INTEGER, 2);
        do {
            System.out.println();
            System.out.println(formatMenu(menuItems, getLoggedUser().getRoles()));
            choice = InputUtils.inputLong(intFieldConfig).intValue();
            MenuItem chosenItem = menuItems.get(choice);
            if(chosenItem != null) {
                Command command = commands.get(chosenItem);
                if(command != null) {
                    try {
                        System.out.println("\n");
                        System.out.println(command.action());
                    } catch (ActionUnsuccessfulException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        } while (true);
    }


    public void printAllContragents() {
        System.out.println(formatTable(contragentColumns, contragentService.getAllContragents()));
    }

    public void printAllProducts() {
        System.out.println(formatTable(productColumns, productService.getAllProducts()));
    }

    public static void main(String[] args) {
        InvoiceRegister register = new InvoiceRegister();
        register.init();
        register.printAllContragents();
        register.printAllProducts();

    }

}
