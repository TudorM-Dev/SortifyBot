package javaFx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.Mat;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class UtilsFx {

    // Utility function to convert a Mat object to a JavaFX Image object
    public static Image mat2Image(Mat mat) {
        try {
            return SwingFXUtils.toFXImage(matToBufferedImage(mat), null);
        } catch (Exception e) {
            System.err.println("Cannot convert the Mat object: " + e);
            return null;
        }
    }

    public static BufferedImage matToBufferedImage(Mat mat) {
        BufferedImage bufferedImage = null;
        try {
            byte[] data = new byte[mat.cols() * mat.rows() * (int) mat.elemSize()];
            int type;
            mat.get(0, 0, data);

            if (mat.channels() == 1) {
                type = BufferedImage.TYPE_BYTE_GRAY;
            } else {
                type = BufferedImage.TYPE_3BYTE_BGR;
            }

            bufferedImage = new BufferedImage(mat.cols(), mat.rows(), type);
            bufferedImage.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
