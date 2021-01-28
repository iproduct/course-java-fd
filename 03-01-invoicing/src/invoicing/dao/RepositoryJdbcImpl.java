package invoicing.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositoryJdbcImpl<K, V extends Identifiable<K>> implements Repository<K,V>, Serializable {
    static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger("i.d.RepositoryJdbcImpl");
    private Properties dbProperties;
    private Connection connection;
//    private Map<K, V> products = new HashMap<>();
//    private KeyGenerator<K> keyGenerator; // has_a = composition

    public RepositoryJdbcImpl(Properties properties){
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
    }

    @Override
    public List<V> findAll() {
         return new ArrayList<>(products.values());
    }

    @Override
    public List<V> findAllSorted(Comparator<V> productComparator) {
        List<V> list = new ArrayList<>(products.values());
        list.sort(productComparator);
        return list;
    }

    @Override
    public List<V> findAllSorted(Comparator<V> productComparator, boolean reverse) {
        return findAllSorted(productComparator.reversed());
    }

    @Override
    public V findById(K id) {
        return products.get(id); // O(1)
    }

    @Override
    public V create(V product) {
        product.setId(keyGenerator.getNextId());
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public V update(V product) {
        if(products.replace(product.getId(), product) == null) {
            return null;
        }
        return product;
    }

    @Override
    public V deleteById(K id) {
        return products.remove(id);
    }

    @Override
    public long count() {
        return 0;
    }
}
