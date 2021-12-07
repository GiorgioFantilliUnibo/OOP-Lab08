package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * {@inheritDoc}
 */
public class ControllerImpl implements Controller {

    private String currentString;
    private final List<String> history = new LinkedList();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextString(final String s) {
        this.currentString = Objects.requireNonNull(s, "The string to set can not be null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextString() {
        return this.currentString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getPrintHistory() {
        return new LinkedList<String>(this.history);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print() {
        if (this.currentString != null) {
            this.history.add(this.currentString);
            System.out.println(this.currentString);
        } else {
            throw new IllegalStateException("A new next string must be set");
        }
    }

}
