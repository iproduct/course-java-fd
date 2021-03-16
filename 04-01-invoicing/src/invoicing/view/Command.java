package invoicing.view;

@FunctionalInterface
public interface Command {
    String execute();
}
