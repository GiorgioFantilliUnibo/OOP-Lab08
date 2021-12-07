package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int FILE_LINE = 3;
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * Build a new {@link DrawNumberApp}.
     * 
     * @param configFilePath
     *          path of the configuration file as string
     */
    @SuppressWarnings({ "PMD.AvoidCatchingGenericException", "PMD.AvoidCatchingNPE" })
    public DrawNumberApp(final String configFilePath) {
        Configuration configuration;
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream(configFilePath);

        this.view = new DrawNumberViewImpl();

        if (inputStream == null) {
            configuration = Configuration.getDefaultConfiguration();
            displayUsingDefaultConfig("Error accessing the configuration file!!\n", configuration);
        } else {
            try (BufferedReader readFile = new BufferedReader(
                                           new InputStreamReader(inputStream))) {
                int minRead = ConfigurationImpl.DEFAULT_MIN;
                int maxRead = ConfigurationImpl.DEFAULT_MAX;
                int attemptsRead = ConfigurationImpl.DEFAULT_ATTEMPTS;

                for (int i = 0; i < FILE_LINE; i++) {
                    final String[] formatLine = readFile.readLine().split(":");

                    if (formatLine.length == 2) {
                        final int value = Integer.parseInt(formatLine[1].trim());
                        if (formatLine[0].contains("min")) {
                            minRead = value;
                        } else if (formatLine[0].contains("max")) {
                            maxRead = value;
                        } else if (formatLine[0].contains("attempts")) {
                            attemptsRead = value;
                        } else {
                            throw new IOException();
                        }
                    } else {
                        throw new IOException();
                    }

                }
                configuration = new ConfigurationImpl(minRead, maxRead, attemptsRead);

            } catch (IOException
                     | NumberFormatException
                     | NullPointerException e) {
                configuration = Configuration.getDefaultConfiguration();
                displayUsingDefaultConfig("Illegal configuration file formatting.\n", configuration);
            }
        }

        this.model = new DrawNumberImpl(configuration);
        this.view.setObserver(this);
        this.view.start();
    }

    private void displayUsingDefaultConfig(final String message, final Configuration configuration) {
        view.displayError(message + "Using default configuration:"
                + "\nmin: " + configuration.getMin()
                + "\nmax: " + configuration.getMax()
                + "\nattempts: " + configuration.getAttempts());
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp("config.yml");
    }

}
