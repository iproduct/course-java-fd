package invoicing.view.command;
import exception.InvalidEntityDataException;
import invoicing.domain.ProductService;
import invoicing.model.Product;
import invoicing.util.InputUtil;
import invoicing.view.Command;

public class InputProductCommand implements Command {
    private ProductService productService;

    public InputProductCommand(ProductService productService) {
        this.productService = productService;
    }

    public String execute() {
        Product p = new Product();
        if(InputUtil.inputProduct(p)) {
            try {
                productService.addProduct(p);
                return "Product added successfully.";
            } catch (InvalidEntityDataException e) {
                return "Error inputting product: " + e.getMessage();
            }
        } else {
            return "Inputting product cnaceled";
        }
    }
}
