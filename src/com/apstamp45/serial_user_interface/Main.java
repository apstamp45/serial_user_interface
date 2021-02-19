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
 * This project uses the JSSC library to create and interface between a 
 * Mac and a serial device.
 * @see <a href=
 *      "https://github.com/scream3r/java-simple-serial-connector/releases/tag/v2.8.0">JSSC</a>
 * @author apstamp45
 */
public class Main {

	/** 
	 * Defines the baud rate at which the computer 
	 * and serial device will communicate.
	 */
	public static int baudRate;

	/** Stores the names of all of the available serial ports. */
	public static String[] ports;

	/** This String stores the adress of the serial device's port. */
	public static String serialPortAdress;

	/** The serial port */
	private static SerialPort serialPort;

	/**
	 * Runs the window.
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		Window.runWindow(args);
	}

	/** Runs when the window loads. */
	public static void start() {
		baudRate = Integer.decode(Window.baudRate.getValue()).intValue();
		getPorts();
		Window.port.getItems().setAll(ports);
	}

	/** Runs when the window closes. */
	public static void close() {
		closePort();
	}
	
	/** Refreshes the list of port names. */
	public static void getPorts() {
		ports = SerialPortList.getPortNames("/dev/", Pattern.compile("tty\\..*"));
	}

	/** Opens the serial port. */
	public static void openPort() {
		try {
			closePort();
			serialPort = new SerialPort(serialPortAdress);
			serialPort.openPort();
			serialPort.setParams(baudRate, 8, 1, 0);
			SerialEventHandler serialEventHandler = new SerialEventHandler();
			serialPort.addEventListener(serialEventHandler);
			Window.serialIn.clear();
			System.out.println("Serial port \"" + serialPortAdress + "\" opened at " + baudRate + " baud.");
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
				System.out.println("Serial port \"" + serialPortAdress + "\" closed.");
			}
		} catch (SerialPortException e) {
			System.out.println("Port could not be closed.");
			e.printStackTrace();
		}
	}

	/** Runs when data is sent from serial device. */
	public static void recieveData() {
		try {
			String recievedData = serialPort.readString();
			if (Window.autoScroll) {
				Window.serialIn.appendText(recievedData + "\n");
			} else {
				int caretPosition = Window.serialIn.getCaretPosition();
				Window.serialIn.appendText(recievedData);
				Window.serialIn.positionCaret(caretPosition);
			}
			System.out.println("Data recieved: \"" + recievedData + "\".");
		} catch (SerialPortException e) {
			System.out.println("Data could not be read.");
			e.printStackTrace();
		}
	}

	/** Sends data to the serial device. **/
	public static void sendData() {
		try {
			if (serialPort != null) {
				String data = Window.serialOut.getText();
				if (data.length() > 0) {
					serialPort.writeBytes(data.getBytes());
					System.out.println("Data sent: \"" + data + "\".");
				} else {
					System.out.println("Data could not be sent: no data to send.");
				}
			} else {
				System.out.println("Data could not be sent: serial device not connected.");
			}
		} catch (SerialPortException e) {
			System.out.println("Data could not be sent.");
			e.printStackTrace();
		}
	}
}
