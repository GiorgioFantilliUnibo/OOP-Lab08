package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;

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
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextString() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrintHistory() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print() {
        // TODO Auto-generated method stub

    }

}
