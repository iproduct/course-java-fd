package course.java.invoicing.view.command;

import course.java.invoicing.exception.ActionUnsuccessfulException;
import course.java.invoicing.model.Product;
import course.java.invoicing.service.ProductService;
import course.java.invoicing.util.FieldConfig;
import course.java.invoicing.util.InputUtils;
import course.java.invoicing.view.Command;

import java.util.Arrays;
import java.util.List;

import static course.java.invoicing.util.FieldType.DECIMAL;
import static course.java.invoicing.util.FieldType.UNIT;

public class AppProductCommand implements Command {
    private ProductService productService;

    public AppProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String action() throws ActionUnsuccessfulException {
        List<FieldConfig> productFields = Arrays.asList(new FieldConfig[]{
                new FieldConfig("name", "Product Name"),
                new FieldConfig("code", "Product Code", false, "[A-Z]{2}\\d{3}"),
                new FieldConfig("price", "Price", DECIMAL, 8, 2),
                new FieldConfig("unit", "Unit", UNIT, "0"),
        });
        Product product = new Product();
        InputUtils.inputInstance(productFields, product);
        productService.addProduct(product);

        return String.format("Product %d: %s added successfully.", product.getId(), product.getName());
    }
}
