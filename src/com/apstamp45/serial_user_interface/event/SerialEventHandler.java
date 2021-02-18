package com.apstamp45.serial_user_interface.event;

import com.apstamp45.serial_user_interface.Main;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

/**
* Handles the serial events.
* @author apstamp45
*/
public class SerialEventHandler implements SerialPortEventListener {

	/** Handles serial events. */
	@Override
	public void serialEvent(SerialPortEvent serialPortEvent) {
		if (serialPortEvent.isRXCHAR()) {
			Main.recieveData();
		}
	}
}
