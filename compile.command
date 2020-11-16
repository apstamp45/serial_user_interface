cd "$(dirname "$0")"
find . -name "*.java" > ./sources.txt
javac -d ./bin -cp ./src:./lib/jssc.jar:./lib/javafx-swt.jar:./lib/javafx.base.jar:./lib/javafx.controls.jar:./lib/javafx.fxml.jar:./lib/javafx.graphics.jar:./lib/javafx.media.jar:./lib/javafx.swing.jar:./lib/javafx.web.jar @sources.txt