cd "$(dirname "$0")"
cd ./bin
java -cp .:../lib/jssc.jar --add-modules /lib/libraries/javafx-sdk-11.0.2/lib/javafx.controls.jar, /lib/libraries/javafx-sdk-11.0.2/lib/javafx.fxml.jar com.apstamp45.serial_console_interface.Main /dev/tty.wchusbserial1410