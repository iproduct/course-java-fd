package jdbcsimple;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class JdbcSimpleDemo {
    private static final Logger LOG = Logger.getLogger("j.JdbcsimpleDemo");
    public static final String DB_DRIVER = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/invoicing";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        // 0.Configure properties
        Properties props = new Properties();
        try {
            String dbConfigPath =
                JdbcSimpleDemo.class.getClassLoader().getResource("db.properties").getPath();
            props.load(new FileInputStream(dbConfigPath));
        } catch (NullPointerException | IOException e) {
            LOG.warning("Can not load DB properties from db.properties file.");
            props.setProperty("driver", DB_DRIVER);
            props.setProperty("url", DB_URL);
            props.setProperty("user", DB_USER);
            props.setProperty("password", DB_PASSWORD);
        }
//        props.setProperty("user",DB_USER);
//        props.setProperty("password",DB_PASSWORD);
//        props.setProperty("ssl","false");

        //1. load db driver class (optional)
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("PostgreSQL DB driver loaded successfully.");

        //2. Create DB connection
        try {
            Connection con = DriverManager.getConnection(DB_URL, props);
            //3. Prepare statement
            PreparedStatement ps = con.prepareStatement("SELECT * FROM persons");

            //4. Execute the statement and get ResutSet
            ResultSet rs = ps.executeQuery();

            //5. Process the ResultSet
            while(rs.next()){
                System.out.printf("| %5d | %-15.15s | %-15.15s | %3d |%n",
                        rs.getLong(1),
                        rs.getString("lastname"),
                        rs.getString("firstname"),
                        rs.getInt("age")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
        System.out.println("DB connection created successfully.");



    }
}
