cd "$(dirname "$0")"
cd ./bin
java -cp .:../lib/jssc-2.9.2.jar --module-path ../lib --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web,slf4j.simple com.apstamp45.serial_user_interface.Main