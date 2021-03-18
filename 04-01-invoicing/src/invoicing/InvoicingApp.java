package invoicing;

import invoicing.dao.impl.ProductRepositoryJdbcImpl;
import invoicing.exception.EntityNotFoundException;
import invoicing.exception.InvalidEntityDataException;
import invoicing.dao.impl.LongIdGenerator;
import invoicing.dao.impl.RepositoryMapImpl;
import invoicing.domain.ProductService;
import invoicing.domain.impl.ProductServiceImpl;
import invoicing.model.Product;
import invoicing.model.Unit;
import invoicing.util.FieldConfig;
import invoicing.util.InputUtil;
import invoicing.util.MenuUtil;
import invoicing.util.PrintUtil;
import invoicing.view.Command;
import invoicing.view.MenuItem;
import invoicing.view.command.InputProductCommand;
import jdbcdemo.JdbcDemo;
import org.w3c.dom.ls.LSOutput;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import static invoicing.util.Alignment.*;
import static invoicing.util.Alignment.CENTER;
import static invoicing.util.FieldType.INTEGER;
import static invoicing.util.PrintUtil.formatTable;
import static invoicing.view.MenuItem.*;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

public class InvoicingApp {
    private static final Logger LOG = Logger.getLogger(InvoicingApp.class.getName());
    public static final List<PrintUtil.ColumnDescriptor> METADATA_COLUMNS = List.of(
            new PrintUtil.ColumnDescriptor("created", "Ctreated", 19, CENTER),
            new PrintUtil.ColumnDescriptor("updated", "Updated", 19, CENTER),
            new PrintUtil.ColumnDescriptor("createdById", "Created By", 11, CENTER),
            new PrintUtil.ColumnDescriptor("updatedById", "Updated By", 11, CENTER)
    );
    public static final List<PrintUtil.ColumnDescriptor> PRODUCT_COLUMNS = new ArrayList<>(List.of(
            new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
            new PrintUtil.ColumnDescriptor("code", "Code", 5, LEFT),
            new PrintUtil.ColumnDescriptor("name", "Name", 12, LEFT),
            new PrintUtil.ColumnDescriptor("description", "Description", 25, LEFT),
            new PrintUtil.ColumnDescriptor("price", "Price", 8, RIGHT, 2),
            new PrintUtil.ColumnDescriptor("unit", "Unit", 5, CENTER)
    ));
    public static final List<PrintUtil.ColumnDescriptor> USER_COLUMNS = new ArrayList<>(List.of(
            new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
            new PrintUtil.ColumnDescriptor("firstName", "First Name", 12, LEFT),
            new PrintUtil.ColumnDescriptor("lastName", "Last Name", 12, LEFT),
            new PrintUtil.ColumnDescriptor("username", "Username", 12, LEFT),
            new PrintUtil.ColumnDescriptor("password", "Password", 12, LEFT),
            new PrintUtil.ColumnDescriptor("role", "Role", 5, LEFT)
    ));

    public static final FieldConfig ID_FIELD_CONFIG = new FieldConfig(null, "ID", INTEGER, 10);


    private ProductService productService;
    private Map<MenuItem, Command> commands = new LinkedHashMap<>();

    public InvoicingApp() {
    }

    private void init() {
        String dbConfigPath = JdbcDemo.class.getClassLoader().getResource("db.properties").getPath();
        Properties dbProps = new Properties();
        try {
            dbProps.load(new FileInputStream(dbConfigPath));
        } catch (IOException e) {
            LOG.log(SEVERE, "Unable to open property file: " + dbConfigPath, e);
            System.exit(1);
        }
        productService = new ProductServiceImpl(
                new ProductRepositoryJdbcImpl(dbProps));
//                new RepositoryMapImpl<>(new LongIdGenerator()));

//        List<Product> products = List.of(new Product("BK001", "Thinking in Java",
//                "Good introduction to Java ...", 35.99),
//                new Product("BK002", "UML Distilled",
//                        "UML described briefly ...", 25.50),
//                new Product("AC001", "Whiteboard Markers", "5 colors set", 7.8),
//                new Product("SV001", "Mobile Internet", "On-demand mobile internet package",
//                        10.99, Unit.GB),
//                new Product("DV001", "2 Band Router", "Supports 2.4 GHz and 5.7 GHz bands",
//                        45.5),
//                new Product("CB001", "Network Cable Cat. 6E", "Gbit Eternet cable UTP",
//                        0.72, Unit.M)
//        );
//        for(Product p: products) {
//            try {
//                productService.addProduct(p);
//            } catch (InvalidEntityDataException e) {
//                LOG.log(WARNING, "Error creating product:", e);
//            }
//        }

        // init menu commands
        commands.put(ADD_PRODUCT, new InputProductCommand(productService));
        commands.put(PRINT_PRODUCTS, () ->
                formatTable(PRODUCT_COLUMNS, productService.findProducts(), "Products List") +
                        "\nTotal product count: " + productService.getProductsCount());
        commands.put(DELETE_PRODUCT, () -> {
            Long id = InputUtil.inputLong(ID_FIELD_CONFIG);
            try {
                Product deleted = productService.deleteProductById(id);
                return String.format("Product %s: '%s' deleted successfully.", id, deleted.getName());
            } catch (EntityNotFoundException ex) {
                return ex.getMessage();
            }
        });
        commands.put(EXIT, () -> {
            System.exit(0);
            return "Good Bye.";
        });
    }

    private void run() {
        Map<Integer, MenuItem> mainMenu = new LinkedHashMap<>();
        int index = 0;
        for (MenuItem item : commands.keySet()) {
            mainMenu.put(++index, item);
        }
        MenuUtil.showMenu(mainMenu, commands);
//        System.out.println(new InputProductCommand(productService).execute());
//        System.out.println(
//                formatTable(PRODUCT_COLUMNS, productService.findProducts(), "Products List:"));
    }

    public static void main(String[] args) {
        InvoicingApp app = new InvoicingApp();
        app.init();
        app.run();
    }

}
