package org.iproduct.invoicing.view.commands;

import org.iproduct.invoicing.exceptions.ActionUnsuccessfulException;
import org.iproduct.invoicing.exceptions.EntityAlreadyExistsException;
import org.iproduct.invoicing.model.Product;
import org.iproduct.invoicing.service.ProductService;
import org.iproduct.invoicing.view.FieldConfig;
import org.iproduct.invoicing.view.InputUtils;

import java.util.List;

import static java.util.logging.Level.SEVERE;
import static org.iproduct.invoicing.view.FieldType.DECIMAL;
import static org.iproduct.invoicing.view.FieldType.UNIT;

public class AddProductCommand implements Command {
    private ProductService productService;

    public AddProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String action() throws ActionUnsuccessfulException {
        Product product = new Product();
        InputUtils.inputInstance(List.of(
                new FieldConfig("name", "Product Name"),
                new FieldConfig("code", "Product Code", null, "^[A-Z]{2}\\d{3}$"),
                new FieldConfig("price", "Price", null, DECIMAL, 8, 2),
                new FieldConfig("unit", "Unit", "0", UNIT)
        ), product);
        try {
            productService.addProduct(product);
        } catch (EntityAlreadyExistsException e) {
            throw new ActionUnsuccessfulException(e.getMessage(), e.getCause());
        }
       return String.format("Product %d: %s added successfully.", product.getId(), product.getName());
    }
}
