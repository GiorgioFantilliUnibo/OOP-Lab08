package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * Build a new {@link DrawNumberApp}.
     * 
     * @param configFilePath
     *          path of the configuration file as string
     */
    public DrawNumberApp(final String configFilePath) {
        Configuration configuration;
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream(configFilePath);

        this.view = new DrawNumberViewImpl();

        if (inputStream == null) {
            configuration = ConfigurationImpl.getDefaultConfiguration();
        } else {
            try (BufferedReader readFile = new BufferedReader(
                                           new InputStreamReader(inputStream))) {
                
            } catch (IOException e) {
                view.displayError(e.getMessage());
            }
        }

        this.model = new DrawNumberImpl(configuration);
        this.view.setObserver(this);
        this.view.start();
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
        new DrawNumberApp("contig.yml");
    }

}
