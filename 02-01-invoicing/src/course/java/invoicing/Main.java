package course.java.invoicing;

import course.java.invoicing.dao.ProductRepository;
import course.java.invoicing.dao.ProductRepositoryMockArrays;
import course.java.invoicing.dao.ProductRepositoryMockList;
import course.java.invoicing.model.Product;
import course.java.invoicing.util.ProductPriceComparator;

import java.util.Comparator;
import java.util.function.Function;

import static course.java.invoicing.model.Unit.M;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("BK001", "Thinking in Java", 35.5);
        Product p2 = new Product("BK002", "UML Distilled", 28.7);
        Product p3 = new Product("CB002", "Network cable Cat.6", 2.25, M);
        Product p4 = new Product("BK001", "Thinking in Java", 35.5);

        Product[] products = {p1, p2, p3, p4};
        ProductRepository productRepo = ProductRepositoryMockList.getInstance();

        for(Product p: products) {
            productRepo.create(p);
        }

        Comparator<Product> priceComparator = (pr1, pr2) -> (int) Math.signum(pr1.getPrice() - pr2.getPrice());

        productRepo.findAllSorted(priceComparator, true)
                .forEach(System.out::println);
        System.out.println();

//        Product umlBook = productRepo.findById(2L);
//        umlBook.setPrice(42.0);
//        productRepo.update(umlBook);
//        productRepo.deleteById(1L);
//
//        productRepo.findAll().forEach(System.out::println);
    }
}
