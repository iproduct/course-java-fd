package course.java.invoicing.dao;

import course.java.invoicing.model.Product;

import java.io.Serializable;

public class AllRepos implements Serializable {
    private ProductRepository productRepository;
    private ContragentRepository contragentRepository;
    private UserRepository userRepository;

    public AllRepos(ProductRepository productRepository, ContragentRepository contragentRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.contragentRepository = contragentRepository;
        this.userRepository = userRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public ContragentRepository getContragentRepository() {
        return contragentRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
