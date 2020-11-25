package com.apstamp45.serial_user_interface.window;

import com.apstamp45.serial_user_interface.Main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class handles the JavaFX window.
 * @author apstamp45
 * @see @see <a href="https://openjfx.io">JavaFX</a>
 * @since 10/27/2020
 */
public class Window extends Application {

    /** Defines the css file for the window. */
    public final String CSS_FILE = "./style.css";

    /** Defines the default baud rates to chose from. */
    public final String[] BAUD_RATES = {"110", "300", "1200", "4800", "9600", "14400", 
        "19200", "38400", "57600", "15200", "128000", "256000"};

    /** Used to select the serial port. */
    public static ChoiceBox<String> port;

    /** Refreshes the list of available ports. */
    public static Button refreshButton;

    /** Used to select the baud rate. */
    public static ChoiceBox<String> baudRate;

    /**
     * Shows the serial data that was
     * sent by the serial device.
     */
    public static TextArea serialIn;

    /** Used to send data to the serial device. */
    public static TextField serialOut;

    /**
     * Sends the data in serialOut to the
     * serial device.
     * @see serialOut
     */
    public static Button send;

    /** Opens a window. */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Top HBox
        Label selectPortLabel = new Label("Select Port:");
        port = new ChoiceBox<>();
        refreshButton = new Button("Refresh");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label selectBaudLabel = new Label("Select Baud Rate:");
        baudRate = new ChoiceBox<>();
        baudRate.getItems().addAll(BAUD_RATES);
        baudRate.setValue("9600");
        HBox topHbox = new HBox(selectPortLabel, port, refreshButton, 
            spacer, selectBaudLabel, baudRate);
        topHbox.setPadding(new Insets(5));
        topHbox.setSpacing(5);

        // TextArea
        serialIn = new TextArea();
        VBox.setVgrow(serialIn, Priority.ALWAYS);
        serialIn.setEditable(false);

        // Bottom HBox
        serialOut = new TextField();
        HBox.setHgrow(serialOut, Priority.ALWAYS);
        send = new Button("Send");
        HBox bottomHBox = new HBox(serialOut, send);
        bottomHBox.setPadding(new Insets(5));
        bottomHBox.setSpacing(5);

        // VBox
        VBox vBox = new VBox(topHbox, serialIn, bottomHBox);

        // Main scene
        Scene mainScene = new Scene(vBox);
        mainScene.getStylesheets().add(CSS_FILE);

        // Main Stage
        primaryStage.setScene(mainScene);
        primaryStage.setWidth(512);
        primaryStage.setHeight(512);
        primaryStage.setMinWidth(354);
        primaryStage.setMinHeight(354);
        primaryStage.setTitle("Serial-User Interface");
        primaryStage.show();

        // Start the main program
        Main.start();
    }

    /**
     * Runs the window.
     * @param args The command line inputs.
     */
    public static void runWindow(String[] args) {
        Window.launch(args);
    }
}
