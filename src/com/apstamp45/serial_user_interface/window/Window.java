package com.apstamp45.serial_user_interface.window;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class handles the JavaFX window.
 * @author apstamp45
 * @see @see <a href="https://openjfx.io">JavaFX</a>
 * @since 10/27/2020
 */
public class Window extends Application {

    /** Opens a window. */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Serial-User Interface");
        primaryStage.show();
    }

    /**
     * Runs the window.
     * @param args The command line inputs.
     */
    public static void runWindow(String[] args) {
        Window.launch(args);
    }
}
