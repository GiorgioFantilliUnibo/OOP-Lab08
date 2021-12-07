package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Output only view.
 */
public class PrintView implements DrawNumberView {

    private final PrintStream stream;

    /**
     * Builds a {@link PrintView} that writes on a given stream.
     * 
     * @param stream 
     *          the {@link PrintView} to write to
     */
    public PrintView(final PrintStream stream) {
        this.stream = stream;
    }

    /**
     * Builds a {@link PrintView} that writes on a indicated file.
     * 
     * @param path 
     *          the file path
     * @throws FileNotFoundException 
     */
    public PrintView(final String path) throws FileNotFoundException {
        this.stream = new PrintStream(new FileOutputStream(new File(path)));
    }

    /**
     * Do nothing, it is a output-only view.
     */
    @Override
    public void setObserver(final DrawNumberViewObserver observer) { }

    /**
     * Do nothing, it is a output-only view.
     */
    @Override
    public void start() { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void numberIncorrect() {
        this.stream.print("\nNumber out of bound");
    }

    /**
     * Print the result of the last attempt.
     * 
     * @param res
     *          result of the last attempt
     */
    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
        case YOU_WON:
            this.stream.print("\n" + res.getDescription());
            break;
        default:
            this.stream.print("\nUnexpected result: " + res);
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void limitsReached() {
        this.stream.print("\nYou lost! Reached the maximum number of attempts.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(final String message) {
        this.stream.print(message);
    }

}
