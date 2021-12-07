package it.unibo.oop.lab.advanced;

/**
 * Configuration of the game.
 */
public interface Configuration {

    /**
     * Indicates whether the loaded configuration is consistent or not.
     * 
     * @return true if the loaded configuration is consistent, false otherwise
     */
    boolean isConsistent();

    /**
     * @return the lower value assumed by the number.
     */
    int getMin();

    /**
     * @return the greater value assumed by the number.
     */
    int getMax();

    /**
     * @return the maximum number of possible attempts
     */
    int getAttempts();
}
