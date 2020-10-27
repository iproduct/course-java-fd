package course.java.invoicing.view;

import course.java.invoicing.exception.ActionUnsuccessfulException;

@FunctionalInterface
public interface Command {
    String action() throws ActionUnsuccessfulException;
}
