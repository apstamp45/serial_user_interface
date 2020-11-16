package com.apstamp45.serial_user_interface.event;

import com.apstamp45.serial_user_interface.Main;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

/**
 * Handles the serial port events.
 * @author apstamp45
 * @since 10/23/20
 */
public class SerialEventHandler implements SerialPortEventListener {

    /**
     * Handles the serial events.
     * @param event The serial event.
     */
    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR()) {
            Main.onDataSend();
        }
    }
}
