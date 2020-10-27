package course.java.invoicing;

import course.java.invoicing.dao.*;
import course.java.invoicing.exception.NonexistingEntityException;
import course.java.invoicing.model.*;
import course.java.invoicing.service.*;
import course.java.invoicing.util.*;
import course.java.invoicing.view.MenuItem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static course.java.invoicing.model.Unit.M;
import static course.java.invoicing.util.Alignment.*;
import static course.java.invoicing.util.FieldType.*;
import static course.java.invoicing.util.PrintUtils.*;

public class InvoiceRegister {
    private static Logger LOG = Logger.getLogger("c.j.i.InvoiceRegister");
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
        MenuItem[] items = MenuItem.values();
        List<MenuItem> mainMenu = Arrays.asList(Arrays.copyOf(items, items.length - 1));
        showMenu(mainMenu);

    }

    public void showMenu(List<MenuItem> menuItems){
        int choice;
        FieldConfig intFieldConfig = new FieldConfig(null, "Choose an option", INTEGER, 2);
        do {
            System.out.println();
            System.out.println(formatMenu(menuItems, getLoggedUser().getRoles()));
            choice = InputUtils.inputLong(intFieldConfig).intValue();
        } while(true);
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
