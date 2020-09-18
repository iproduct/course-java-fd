package org.iproduct.invoicing.view.commands;

import org.iproduct.invoicing.exceptions.ActionUnsuccessfulException;

@FunctionalInterface
public interface Command {
    String action() throws ActionUnsuccessfulException;
}
