package invoicing.dao;

import invoicing.model.Product;
import invoicing.model.Unit;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static invoicing.model.Unit.PCS;

public class ProductRepositoryJdbcImpl implements ProductRepository {
    static final String TABLE_NAME = "products";
    private static final Logger LOG = Logger.getLogger("i.d.RepositoryJdbcImpl");
    private Properties dbProperties;
    private Connection connection;
//    private Map<K, Product> products = new HashMap<>();
//    private KeyGenerator<K> keyGenerator; // has_a = composition

    public ProductRepositoryJdbcImpl(Properties properties){
        this.dbProperties = properties;
        try {
            init();
        } catch (ClassNotFoundException e) {
            LOG.log(Level.SEVERE, "Can not load DB driver class.", e);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Can not open DB connection for URL: " + dbProperties.getProperty("url"), e);
        }
    }

    public void init() throws ClassNotFoundException, SQLException {
        Class.forName(dbProperties.getProperty("driver"));
        System.out.println("PostgreSQL DB driver loaded successfully.");
        connection = DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties);
        PreparedStatement ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS products (" +
                " id SERIAL PRIMARY KEY," +
                " code varchar(10) NOT NULL," +
                " name varchar(45) NOT NULL," +
                " description varchar(450) NOT NULL," +
                " price real," +
                " unit integer NOT NULL DEFAULT '0'" +
//                " PRIMARY KEY (username)\n" +
                ")");
        int numExecutesStat = ps.executeUpdate();
        if(numExecutesStat > 0) {
            LOG.warning("Table 'products' was successfully created.");
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setCode(rs.getString("code"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                int unitOrdinal = rs.getInt("unit");
                for(Unit u : Unit.values()) {
                    if(u.ordinal() == unitOrdinal) {
                        p.setUnit(u);
                        break;
                    }
                }
                products.add(p);
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "findAll: can not prepared statement.", e);
        }
        return products;
    }

    @Override
    public List<Product> findAllSorted(Comparator<Product> productComparator) {
        List<Product> list = new ArrayList<>();
        list.sort(productComparator);
        return list;
    }

    @Override
    public List<Product> findAllSorted(Comparator<Product> productComparator, boolean reverse) {
        return findAllSorted(productComparator.reversed());
    }

    @Override
    public Product findById(Long id) {
        return null; // O(1)
    }

    @Override
    public Product create(Product product) {
//        product.setId(keyGenerator.getNextId());
//        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product update(Product product) {
//        if(products.replace(product.getId(), product) == null) {
//            return null;
//        }
        return product;
    }

    @Override
    public Product deleteById(Long id) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
