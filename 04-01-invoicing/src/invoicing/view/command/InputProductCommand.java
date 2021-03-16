package invoicing.view.command;
import invoicing.model.Product;
import invoicing.view.Command;

public class InputProductCommand implements Command {
    public String execute() {
        Product p = new Product();

        return "Product added successfully.";
    }
}
