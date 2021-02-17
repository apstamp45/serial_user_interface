cd "$(dirname "$0")"
find . -name "*.java" > ./sources.txt
javac -d ./bin -cp ./src:./lib/jssc-2.9.2.jar --module-path ./lib --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web,slf4j.simple @sources.txt