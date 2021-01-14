cd "$(dirname "$0")"
cd ./bin
java --module-path ../lib --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web,jssc,slf4j.simple com.apstamp45.serial_user_interface.Main