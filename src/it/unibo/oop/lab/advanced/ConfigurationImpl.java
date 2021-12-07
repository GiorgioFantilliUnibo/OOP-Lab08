package it.unibo.oop.lab.advanced;

/**
 * {@inheritDoc}
 */
public class ConfigurationImpl implements Configuration {

    private static final int DEFAULT_MIN = 0;
    private static final int DEFAULT_MAX = 100;
    private static final int DEFAULT_ATTEMPTS = 10;

    private final int min;
    private final int max;
    private final int attempts;
    private boolean isConsistent;

    /**
     * Build a new {@link ConfigurationImpl}.
     * 
     * @param min
     *          lower value assumed by the number
     * @param max
     *          greater value assumed by the number
     * @param attempts
     *          maximum number of possible attempts
     */
    public ConfigurationImpl(final int min, final int max, final int attempts) {
        this.min = min;
        this.max = max;
        this.attempts = attempts;
        this.setConsistency();
    }

    /**
     * {@inheritDoc}
     */
    public int getMin() {
        if (this.isConsistent) {
            return min;
        } else {
            return DEFAULT_MIN;
        }
    }

    /**
     * {@inheritDoc}
     */
    public int getMax() {
        if (this.isConsistent) {
            return max;
        } else {
            return DEFAULT_MAX;
        }
    }

    /**
     * {@inheritDoc}
     */
    public int getAttempts() {
        if (this.isConsistent) {
            return attempts;
        } else {
            return DEFAULT_ATTEMPTS;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConsistent() {
        return this.isConsistent;
    }

    private void setConsistency() {
        this.isConsistent = this.attempts > 0 && this.min < this.max;
    }

}
