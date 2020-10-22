package course.java.invoicing;

import course.java.invoicing.dao.*;
import course.java.invoicing.model.Product;
import course.java.invoicing.util.ProductPriceComparator;

import java.security.Key;
import java.util.Comparator;
import java.util.function.Function;

import static course.java.invoicing.model.Unit.M;
import static course.java.invoicing.util.PrintUtils.entitiesToString;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("BK001", "Thinking in Java", 35.5);
        Product p2 = new Product("BK002", "UML Distilled", 28.7);
        Product p3 = new Product("CB002", "Network cable Cat.6", 2.25, M);
        Product p4 = new Product("BK001", "Thinking in Java", 35.5);

        Product[] products = {p1, p2, p3, p4};
        KeyGenerator<Long> keyGenerator = new LongKeyGenerator();
        Repository<Long, Product> productRepo = new MockRepository<>(keyGenerator);

        for(Product p: products) {
            productRepo.create(p);
        }

        System.out.println(entitiesToString(productRepo.findAll()));
        System.out.println();

        Comparator<Product> priceComparator = (pr1, pr2) -> (int) Math.signum(pr1.getPrice() - pr2.getPrice());

        System.out.println(entitiesToString(productRepo.findAllSorted(priceComparator)));
        System.out.println();

        Product umlBook = productRepo.findById(2L); //O(1)
        System.out.println(umlBook);
        System.out.println();

        umlBook.setPrice(42.0);
        productRepo.update(umlBook); //O(1)
        System.out.println("---------------------------------");

        productRepo.deleteById(1L); //O(1)

        System.out.println(entitiesToString(productRepo.findAll()));


    }
}
