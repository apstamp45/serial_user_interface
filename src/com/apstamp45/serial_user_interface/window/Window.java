package com.apstamp45.serial_user_interface.window;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
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

    /** Opens a window. */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Top HBox
        MenuButton port = new MenuButton("Select Port");
        Button refreshButton = new Button("Refresh");
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        Label portLabel = new Label("LABEL");
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        MenuButton baudRate = new MenuButton("Select Baud");
        HBox topHbox = new HBox(port, refreshButton, 
            spacer1, portLabel, spacer2, baudRate);
        topHbox.setPadding(new Insets(5));
        topHbox.setSpacing(5);

        // TextArea
        TextArea serialIn = new TextArea();
        VBox.setVgrow(serialIn, Priority.ALWAYS);
        serialIn.setEditable(false);

        // Bottom HBox
        TextField serialOut = new TextField();
        HBox.setHgrow(serialOut, Priority.ALWAYS);
        Button send = new Button("Send");
        HBox bottomHBox = new HBox(serialOut, send);
        bottomHBox.setPadding(new Insets(5));
        bottomHBox.setSpacing(5);

        // VBox
        VBox vBox = new VBox(topHbox, serialIn, bottomHBox);

        // Main scene
        Scene mainScene = new Scene(vBox);

        // Main Stage
        primaryStage.setScene(mainScene);
        primaryStage.setWidth(512);
        primaryStage.setHeight(512);
        primaryStage.setMinWidth(354);
        primaryStage.setMinHeight(354);
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
