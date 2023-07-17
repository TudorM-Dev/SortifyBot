package home;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Slider clawSlider;

    @FXML
    private TextField clawValue;

    @FXML
    private Slider heightSlider;

    @FXML
    private TextField heightValue;

    @FXML
    private Slider jointAngleSlider;

    @FXML
    private TextField jointAngleValue;

    @FXML
    private Slider wristAngleSlider;

    @FXML
    private TextField wristAngleValue;

    @FXML
    private Slider baseAngleSlider;

    @FXML
    private TextField baseAngleValue;

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlSettings;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handleSlider();

        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));


                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #040516");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnSettings)
        {
            pnlSettings.setStyle("-fx-background-color : #040516");
            pnlSettings.toFront();
        }
    }

    public void handleInput(ActionEvent actionEvent){
        if(actionEvent.getSource() == baseAngleValue) baseAngleSlider.setValue(Double.parseDouble(baseAngleValue.getText()));
        else if(actionEvent.getSource() == heightValue) heightSlider.setValue(Double.parseDouble(heightValue.getText()));
        else if(actionEvent.getSource() == jointAngleValue) jointAngleSlider.setValue(Double.parseDouble(jointAngleValue.getText()));
        else if(actionEvent.getSource() == wristAngleValue) wristAngleSlider.setValue(Double.parseDouble(wristAngleValue.getText()));
        else if(actionEvent.getSource() == clawValue) clawSlider.setValue(Double.parseDouble(clawValue.getText()));
    }

    public void handleSlider(){
        baseAngleValue.setText(String.valueOf(baseAngleSlider.getValue()));
        heightValue.setText(String.valueOf(heightSlider.getValue()));
        jointAngleValue.setText(String.valueOf(jointAngleSlider.getValue()));
        wristAngleValue.setText(String.valueOf(wristAngleSlider.getValue()));
        clawValue.setText(String.valueOf(clawSlider.getValue()));
    }
}
