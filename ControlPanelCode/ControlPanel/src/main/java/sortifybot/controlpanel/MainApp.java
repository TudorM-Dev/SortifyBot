package sortifybot.controlpanel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class MainApp extends Application {
    double x,y = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent parent  =  FXMLLoader.load(getClass().getResource("load.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        parent .setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        parent .setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });

        primaryStage.setScene(new Scene(parent , 700, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
