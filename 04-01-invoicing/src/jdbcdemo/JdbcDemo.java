package jdbcdemo;

import invoicing.model.Product;
import invoicing.model.Unit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        try {
            Connection con = DriverManager.getConnection(dbProps.getProperty("url"), dbProps);
            System.out.printf("DB connection created successfully: %s%n", dbProps.getProperty("url"));

            // 3. Create and execute db statements
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO products (code, name, description, price, unit) VALUES (?, ?, ?, ?, ?)");
//            int numInserts = 0;
//            for(Product p: products){
//                ps.setString(1, p.getCode());
//                ps.setString(2, p.getName());
//                ps.setString(3, p.getDescription());
//                ps.setDouble(4, p.getPrice());
//                ps.setInt(5, p.getUnit().ordinal());
//                numInserts += ps.executeUpdate();
//            }
//            System.out.printf("%d records inserted successfully.", numInserts);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
