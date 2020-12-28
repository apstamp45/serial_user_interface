package com.apstamp45.serial_user_interface;

import java.util.regex.Pattern;

import com.apstamp45.serial_user_interface.event.SerialEventHandler;
import com.apstamp45.serial_user_interface.window.Window;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * This project uses the JSSC library to create and interface between the
 * computer and an serial device (on Mac).
 * 
 * @see <a href=
 *      "https://github.com/scream3r/java-simple-serial-connector/releases/tag/v2.8.0">JSSC</a>
 * @author apstamp45
 * @since 10/23/20
 */
public class Main {

	public static int baudRate;
	/** Stores all of the available serial ports. */
	public static String[] ports;

	/**
	 * This String stores the adress of the serial device's port.
	 */
	public static String serialPortAdress;

	/** The serial port */
	private static SerialPort serialPort;

	/**
	 * The main method.
	 * 
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		Window.runWindow(args);
		// if (args.length > 0) {
		// 	String[] ports = SerialPortList.getPortNames("/dev/", Pattern.compile("tty.*"));
		// 	for (String portName : ports) {
		// 		if (portName.equals(args[0])) {
		// 			serialPortAdress = args[0];
		// 		}
		// 	}
		// 	if (serialPortAdress == null) {
		// 		System.out.println("Your port could not be found.");
		// 		if (ports.length == 0) {
		// 			System.out.println("No ports available.");
		// 		} else {
		// 			System.out.println("Here's a list of the available ports:");
		// 			for (int i = 0; i < ports.length; i++) {
		// 				System.out.println(i + ". " + ports[i]);
		// 			}
		// 		}
		// 		System.exit(1);
		// 	}
		// } else {
		// 	System.out.println("Usage: java com.apstamp45.serial_console_interface.Main <port adress>");
		// 	String[] ports = SerialPortList.getPortNames("/dev/", Pattern.compile("tty.wchusbserial*"));
		// 	if (ports.length == 0) {
		// 		System.out.println("No ports available.");
		// 	} else {
		// 		System.out.println("Here's a list of the available ports:");
		// 		for (int i = 0; i < ports.length; i++) {
		// 			System.out.println(i + ". " + ports[i]);
		// 		}
		// 	}
		// 	System.exit(1);
		// }
		// Window.launch(args);
		// serialPort = new SerialPort(serialPortAdress);
		// try {
		// 	if (serialPort.openPort()) {
		// 		System.out.println("Port opened.");
		// 	}
		// 	serialPort.setParams(9600, 8, 1, 0);
		// 	serialPort.addEventListener(new SerialEventHandler());
		// 	String out;
		// 	while (true) {
		// 		out = JOptionPane.showInputDialog("Send text (or :X to exit)");
		// 		if (out.equalsIgnoreCase(":X")) {
		// 			break;
		// 		}
		// 	}
		// 	serialPort.closePort();
		// } catch (SerialPortException e) {
		// 	e.printStackTrace();
		// }
	}

	/** Runs when the window loads. */
	public static void start() {
		getPorts();
		Window.port.getItems().addAll(ports);
	}

	/** Runs when the window closes. */
	public static void close() {
		closePort();
	}
	
	/** Refreshes the port list. */
	public static void getPorts() {
		ports = SerialPortList.getPortNames("/dev/", Pattern.compile("tty.*"));
	}

	/** Runs when data is sent from serial port. */
	public static void onDataSend() {
		try {
			String in = serialPort.readString();
			Window.serialIn.appendText(in);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	/** Opens the serial port. */
	public static void openPort() {
		try {
			closePort();
			serialPortAdress = Window.port.getSelectionModel().getSelectedItem();
			serialPort = new SerialPort(serialPortAdress);
			serialPort.openPort();
			serialPort.setParams(baudRate, 8, 1, 0);
			serialPort.addEventListener(new SerialEventHandler());
		} catch (SerialPortException e) {
			System.out.println("Port could not be opened.");
			e.printStackTrace();
		}
	}

	/** Closes the serial port. */
	public static void closePort() {
		try {
			if (serialPort != null && serialPort.isOpened()) {
				serialPort.closePort();
			}
		} catch (SerialPortException e) {
			System.out.println("Port could not be closed.");
			e.printStackTrace();
		}
	}
}
