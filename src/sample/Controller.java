package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    private Pane pane;

    @FXML
    private Slider redS;

    @FXML
    private Slider greenS;

    @FXML
    private Slider blueS;

    @FXML
    private Slider opacityS;

    @FXML
    private Label hexLabel;

    @FXML
    void initialize(){
        ChangeListener<Number> listener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String hexCode = "";
                double d = opacityS.getValue();
                hexCode += doubleToHex(redS.getValue(), d);
                hexCode += doubleToHex(greenS.getValue(), d);
                hexCode += doubleToHex(blueS.getValue(), d);
                pane.setStyle("-fx-background-color: #" + hexCode);
                hexLabel.setText("#"+ hexCode);
            }
        };
        redS.valueProperty().addListener(listener);
        greenS.valueProperty().addListener(listener);
        opacityS.valueProperty().addListener(listener);
        blueS.valueProperty().addListener(listener);
    }

    private String doubleToHex(double d, double opacity){
        if(d == 0.0){
            return "00";
        }
        double o = (opacity / 100);
        int i = (int) (d * 2.55 * o);
        String s = Integer.toHexString(i);
        if(s.length() == 1){
            return "0" + s;
        }
        return s;
    }
}
