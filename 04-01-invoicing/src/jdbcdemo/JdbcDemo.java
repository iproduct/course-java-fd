package jdbcdemo;

import invoicing.InvoicingApp;
import invoicing.model.Product;
import invoicing.model.Unit;
import invoicing.util.PrintUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcDemo {
    public static final List<Product> products = List.of(new Product("BK001", "Thinking in Java",
                    "Good introduction to Java ...", 35.99),
            new Product("BK002", "UML Distilled",
                    "UML described briefly ...", 25.50),
            new Product("AC001", "Whiteboard Markers", "5 colors set", 7.8),
            new Product("SV001", "Mobile Internet", "On-demand mobile internet package",
                    10.99, Unit.GB),
            new Product("DV001", "2 Band Router", "Supports 2.4 GHz and 5.7 GHz bands",
                    45.5),
            new Product("CB001", "Network Cable Cat. 6E", "Gbit Eternet cable UTP",
                    0.72, Unit.M)
    );

    public static final String INSERT_PRODUCT_SQL = "INSERT INTO products (code, name, description, price, unit) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_PRODUCTS_SQL = "SELECT * FROM products";

    public static void main(String[] args) throws IOException {
        String dbConfigPath = JdbcDemo.class.getClassLoader().getResource("db.properties").getPath();
        Properties dbProps = new Properties();
        dbProps.load(new FileInputStream(dbConfigPath));

        // 1. Load DB Driver (optional)
        try {
            Class.forName(dbProps.getProperty("driver"));
        } catch(ClassNotFoundException ex) {
            System.err.printf("Database driver %s not found.", dbProps.getProperty("driver"));
            System.exit(0);
        }
        System.out.println("Program completed successfully.");

        // 2. Connect to DB
        try (Connection con = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)){
            System.out.printf("DB connection created successfully: %s%n", dbProps.getProperty("url"));

            // 3. Create and execute db statements
            int numInserts;
            try (PreparedStatement ps = con.prepareStatement(INSERT_PRODUCT_SQL)) {
                con.setAutoCommit(false);
                numInserts = 0;
                for (Product p : products) {
                    ps.setString(1, p.getCode());
                    ps.setString(2, p.getName());
                    ps.setString(3, p.getDescription());
                    ps.setDouble(4, p.getPrice());
                    ps.setInt(5, p.getUnit().ordinal());
                    numInserts += ps.executeUpdate();
                }
                con.commit();
            } catch (SQLException ex){
                con.rollback();
                throw ex;
            } finally {
                con.setAutoCommit(true);
            }
            System.out.printf("%d records inserted successfully.", numInserts);

            try (Statement s = con.createStatement()) {
                ResultSet rs = s.executeQuery(SELECT_ALL_PRODUCTS_SQL);
                List<Product> products = new ArrayList<>();
                while(rs.next()) {
                    Product p = new Product(
                            rs.getLong("id"),
                            rs.getString("code"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            Unit.values()[rs.getInt("unit")]
                    );
                    products.add(p);
                }
                System.out.println(
                        PrintUtil.formatTable(InvoicingApp.PRODUCT_COLUMNS, products, "Products List"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
