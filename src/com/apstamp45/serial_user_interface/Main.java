package com.apstamp45.serial_user_interface;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.apstamp45.serial_user_interface.window.Window;

import javafx.application.Application;
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

	/**
	 * This String stores the adress of the serial device's port.
	 */
	private static String serialPortAdress;

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
		// 	String[] ports = SerialPortList.getPortNames("/dev/", Pattern.compile("tty.wchusbserial*"));
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

	}

	public static void onDataSend() {
		try {
			String in = serialPort.readString();
			System.out.println(in);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}
}