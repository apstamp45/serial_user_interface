package com.apstamp45.serial_user_interface;

import java.util.regex.Pattern;

import com.apstamp45.serial_user_interface.event.SerialEventHandler;
import com.apstamp45.serial_user_interface.window.Window;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
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
	}

	/** Runs when the window loads. */
	public static void start() {
		getPorts();
		Window.port.getItems().addAll(ports);
		baudRate = Integer.decode(Window.baudRate.getValue()).intValue();
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
			if (Window.autoScroll) {
				Window.serialIn.appendText(in);
			} else {
				int caretPosition = Window.serialIn.getCaretPosition();
				Window.serialIn.appendText(in);
				Window.serialIn.positionCaret(caretPosition);
			}
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
			System.out.println(baudRate);
			serialPort.setParams(baudRate, 8, 1, 0);
			SerialEventHandler serialEventHandler = new SerialEventHandler();
			serialPort.addEventListener(serialEventHandler);
			System.out.println(serialEventHandler);
			System.out.println("port opened");
			System.out.println(serialPortAdress);
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
				System.out.println("port closed");
			}
		} catch (SerialPortException e) {
			System.out.println("Port could not be closed.");
			e.printStackTrace();
		}
	}
}
