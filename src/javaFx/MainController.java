package javaFx;

import ColorDetectionCV.WebcamColorDetector;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import javax.swing.text.Element;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
public class MainController implements Initializable {

    private static final DecimalFormat dfZero = new DecimalFormat("0.00");
    String path = "servoSliderValue.txt";

    @FXML
    private Slider slider1;
    @FXML
    private Slider slider2;
    @FXML
    private Slider slider3;
    @FXML
    private Slider slider4;
    @FXML
    private Slider slider5;
    @FXML
    private Slider slider6;
    @Override

    public void initialize(URL location, ResourceBundle resources) {
        //smash

//        WebcamColorDetector webcamColorDetector = new WebcamColorDetector();

        Thread fileWriter = new Thread(() -> {
            while (true) {
                try (FileWriter writer = new FileWriter(path)) {
                    writer.write(getServoData());
                    System.out.println("Test");
                } catch (IOException e) {
                    System.out.println("Error");
                }
            }
        });
        fileWriter.start();

//        Thread imagineThread = new Thread(() -> {
//            while (true) {
//                BufferedImage bufImage = UtilsFx.matToBufferedImage(webcamColorDetector.getOutput());
//                Image image = SwingFXUtils.toFXImage(bufImage, null);
//                imgView.setImage(image);
//            }
//        });
//        imagineThread.start();


    }

    String getServoData() {
        return dfZero.format(slider1.getValue()) + ' ' + dfZero.format(slider2.getValue()) + ' ' + dfZero.format(slider3.getValue()) + ' ' + dfZero.format(slider4.getValue());
    }



}

