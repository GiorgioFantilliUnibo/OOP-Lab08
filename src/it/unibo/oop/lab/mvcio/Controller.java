package it.unibo.oop.lab.mvcio;

import java.io.File;

/**
 * 
 */
public class Controller {

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */

    private File file = new File(System.getProperty("user.home")
                                    + System.getProperty("file.separator")
                                    + "output.txt");
    /**
     * Set the {@link file} argument as the current file.
     * 
     * @param file
     *          file to be set as current
     * @throws IllegalArgumentException
     *          if do not exist the entire path of the {@link file} argument
     */
    public void setCurrentFile(final File file) {
        if (file.getParentFile().exists()) {
            this.file = file;
        } else {
            throw new IllegalArgumentException("Not existing folder.");
        }
    }

    /**
     * Set the file indicated by {@link path} argument as the current file.
     * 
     * @param path
     *          path of the file
     * @throws IllegalArgumentException
     *          if do not exist the entire {@link path} argument
     */
    public void setCurrentFile(final String path) {
        this.setCurrentFile(new File(path));
    }

    /**
     * Return the current file.
     * 
     * @return the current file
     */
    public File getCurrentFile() {
        return this.file;
    }

    /**
     * Return the path of current file.
     * 
     * @return the path of current file as {@link String}
     */
    public String getCurrentFilePath() {
        return this.file.getPath();
    }
}
