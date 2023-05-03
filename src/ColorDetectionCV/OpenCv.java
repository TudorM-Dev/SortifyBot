package ColorDetectionCV;

import ColorDetectionCV.WebcamColorDetector;
import org.opencv.core.Core;

public class OpenCv {
    public static void main(String[] args){
        WebcamColorDetector webcamColorDetector = new WebcamColorDetector();

        while (true){
            System.out.println(webcamColorDetector.getColor());
        }
    }
}
