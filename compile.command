cd "$(dirname "$0")"
find . -name "*.java" > ./sources.txt
javac -d ./bin --module-path ./lib --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web,jssc,slf4j.simple @sources.txt