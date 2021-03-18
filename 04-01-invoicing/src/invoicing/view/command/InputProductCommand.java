package invoicing.view.command;
import invoicing.exception.InvalidEntityDataException;
import invoicing.domain.ProductService;
import invoicing.model.Product;
import invoicing.util.FieldConfig;
import invoicing.util.InputUtil;
import invoicing.view.Command;

import java.util.Arrays;
import java.util.List;

import static invoicing.util.FieldType.DECIMAL;
import static invoicing.util.FieldType.UNIT;

public class InputProductCommand implements Command {
    private ProductService productService;

    public InputProductCommand(ProductService productService) {
        this.productService = productService;
    }

    public String execute() {
        List<FieldConfig> productFields = Arrays.asList(new FieldConfig[]{
                new FieldConfig("code", "Product Code", false, "[A-Z]{2}\\d{3}"),
                new FieldConfig("name", "Product Name"),
                new FieldConfig("description", "Product Description"),
                new FieldConfig("price", "Price", DECIMAL, 8, 2),
                new FieldConfig("unit", "Unit", UNIT, "0"),
        });

        Product p = new Product();
        if(InputUtil.inputInstance(productFields, p)) {
            try {
                productService.addProduct(p);
                return String.format("Product %d: %s added successfully.", p.getId(), p.getName());
            } catch (InvalidEntityDataException e) {
                return "Error inputting product: " + e.getMessage();
            }
        } else {
            return "Inputting product cnaceled";
        }
    }
}
