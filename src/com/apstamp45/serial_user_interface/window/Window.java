package com.apstamp45.serial_user_interface.window;

import com.apstamp45.serial_user_interface.Main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
* This class handles the JavaFX window.
* @author apstamp45
* @see @see <a href="https://openjfx.io">JavaFX</a>
*/
public class Window extends Application {

	/** Defines the css file for the window. */
	public final String CSS_FILE = "./style.css";

	/** The minimum window height. */
	public final int MIN_HEIGHT = 144;

	/** The minimum window width. */
	public final int MIN_WIDTH = 512;

	/** The height at which the window is created. */
	public final int WINDOW_HEIGHT_AT_START = 512;

	/** The width at which the window is created. */
	public final int WINDOW_WIDTH_AT_START = 512;

	/** Defines the default baud rates to chose from. */
	public final String[] BAUD_RATES = {"110", "300", "1200", "4800", "9600", "14400", "19200", "38400", "57600", "15200", "128000", "256000"};

	/** Indicates wether or not to auto scroll serialIn. */
	public static boolean autoScroll = true;

	/** Can be used by the user to enable/disable auto scrolling. */
	private static CheckBox autoScrollCheckBox;

	/** Used to select the serial port. */
	public static ChoiceBox<String> port;

	/** Closes and re-opens the serial port. */
	public static Button reopen;

	/** Used to select the baud rate. */
	public static ChoiceBox<String> baudRate;

	/** Shows the serial data that was sent by the serial device. */
	public static TextArea serialIn;

	/** Used to send data to the serial device. */
	public static TextField serialOut;

	/**
	* Sends the data in serialOut to the serial device.
	* @see serialOut
	*/
	public static Button send;

	/** Opens a window. */
	@Override
	public void start(Stage primaryStage) throws Exception {

		//Top HBox
		Label selectPortLabel = new Label("Select Port:");
		port = new ChoiceBox<>();
		port.setMaxWidth(128);
		port.getSelectionModel().selectedItemProperty().addListener(e -> {
			String selectedPort = port.getSelectionModel().getSelectedItem();
			if (selectedPort != null && !selectedPort.equals(Main.serialPortAdress)) {
				Main.serialPortAdress = selectedPort;
				Main.openPort();
			}
		});
		port.setOnMouseReleased(e -> {
			Main.getPorts();
			Window.port.getItems().setAll(Main.ports);
		});
		reopen = new Button("Re-Open");
		reopen.setOnMouseClicked((e) -> {
			Main.closePort();
			Main.openPort();
		});
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		Label selectBaudLabel = new Label("Select Baud Rate:");
		baudRate = new ChoiceBox<>();
		baudRate.getItems().addAll(BAUD_RATES);
		baudRate.setValue("9600");
		baudRate.getSelectionModel().selectedItemProperty().addListener(e -> {
			if (baudRate.getSelectionModel().getSelectedItem() != null) {
				Main.baudRate = Integer.decode(baudRate.getSelectionModel().getSelectedItem()).intValue();
			}
		});
		HBox topHbox = new HBox(selectPortLabel, port, reopen, 
		spacer, selectBaudLabel, baudRate);
		topHbox.setPadding(new Insets(5));
		topHbox.setSpacing(5);

		// TextArea
		serialIn = new TextArea();
		VBox.setVgrow(serialIn, Priority.ALWAYS);
		serialIn.setEditable(false);

		// Bottom HBox
		autoScrollCheckBox = new CheckBox("Auto Scroll");
		autoScrollCheckBox.selectedProperty().set(true);
		autoScrollCheckBox.selectedProperty().addListener(e -> {
			autoScroll = autoScrollCheckBox.selectedProperty().get();
			if (autoScroll == true) {
				System.out.println("Auto scroll: on.");
			} else {
				System.out.println("Auto scroll: off.");
			}
		});
		serialOut = new TextField();
		serialOut.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				Main.sendData();
				serialOut.clear();
			}
		});
		HBox.setHgrow(serialOut, Priority.ALWAYS);
		send = new Button("Send");
		send.setOnMouseReleased(e -> {
			Main.sendData();
			serialOut.clear();
		});
		HBox bottomHBox = new HBox(autoScrollCheckBox, serialOut, send);
		bottomHBox.setPadding(new Insets(5));
		bottomHBox.setSpacing(5);

		// VBox
		VBox vBox = new VBox(topHbox, serialIn, bottomHBox);

		// Main scene
		Scene mainScene = new Scene(vBox);
		mainScene.getStylesheets().add(CSS_FILE);

		// Main Stage
		primaryStage.setScene(mainScene);
		primaryStage.setWidth(WINDOW_WIDTH_AT_START);
		primaryStage.setHeight(WINDOW_HEIGHT_AT_START);
		primaryStage.setMinWidth(MIN_WIDTH);
		primaryStage.setMinHeight(MIN_HEIGHT);
		primaryStage.setTitle("Serial-User Interface");
		primaryStage.show();

		// Start the main program
		Main.start();
	}

	/** Runs just before the window closes. */
	@Override
	public void stop() {
		Main.close();
	}

	/**
	* Runs the window.
	* @param args The command line inputs.
	*/
	public static void runWindow(String[] args) {
		Window.launch(args);
	}
}
