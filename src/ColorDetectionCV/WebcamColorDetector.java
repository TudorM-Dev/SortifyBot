package ColorDetectionCV;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;


public class WebcamColorDetector {
    static Rect squareRect;
    static Mat output;
    static String color;

    public void Main() {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture cap = new VideoCapture(0);
        if (!cap.isOpened()) {
            System.err.println("Could not open webcam");
            System.exit(-1);
        }
        HighGui.namedWindow("Webcam", HighGui.WINDOW_NORMAL);
        int squareSize = 100;
        int squareHalfSize = squareSize / 2;


        // Loop over frames from the video feed
        while (true) {
            // Read a frame from the video feed
            Mat frame = new Mat();
            cap.read(frame);

            // Check if the frame is empty (i.e., end of video feed)
            if (frame.empty()) {
                System.err.println("End of video feed");
                break;
            }

            // Get the dimensions of the frame
            int frameWidth = frame.width();
            int frameHeight = frame.height();

            // Calculate the coordinates of the center of the frame
            int centerX = frameWidth / 2;
            int centerY = frameHeight / 2;

            // Calculate the coordinates of the top-left corner of the square
            int squareX = centerX - squareHalfSize;
            int squareY = centerY - squareHalfSize;

            Imgproc.GaussianBlur(frame, frame, new Size(5, 5), 10);
            squareRect = new Rect(squareX, squareY, squareSize, squareSize);

//            System.out.println("Red = " + (getColorPixel(frame, ColorConstants.redScalar) + getColorPixel(frame, ColorConstants._redScalar)));
//            System.out.println("Green = " + getColorPixel(frame, ColorConstants.greenScalar));
//            System.out.println("Blue = " + getColorPixel(frame, ColorConstants.blueScalar));

            getPrimaryColor(frame, squareRect);

            // Draw the square on the frame
            Imgproc.rectangle(frame, squareRect, new Scalar(0, 255, 0), 2);

            // Display the frame in the window
            HighGui.imshow("Webcam", frame);

            output = frame;

            // Wait for a key event (to exit the program)
            if (HighGui.waitKey(10) != -1) {
                break;
            }
        }


        // Release the resources used by the VideoCapture object and close the window
        cap.release();
        HighGui.destroyAllWindows();
    }

    public WebcamColorDetector(){
        Thread thread = new Thread(() -> {
            Main();
        });
        thread.start();
    }

    static void getPrimaryColor(Mat mat, Rect rect){

        Mat img = new Mat(mat, rect);
        Mat hsv = new Mat();
        Imgproc.cvtColor(img, hsv, Imgproc.COLOR_BGR2HSV);

        Scalar redLower = new Scalar(0, 50, 50);
        Scalar redUpper = new Scalar(10, 255, 255);
        Scalar blueLower = new Scalar(100, 50, 50);
        Scalar blueUpper = new Scalar(130, 255, 255);
        Scalar greenLower = new Scalar(36, 50, 50);
        Scalar greenUpper = new Scalar(70, 255, 255);

        Mat redMask = new Mat();
        Mat blueMask = new Mat();
        Mat greenMask = new Mat();
        Core.inRange(hsv, redLower, redUpper, redMask);
        Core.inRange(hsv, blueLower, blueUpper, blueMask);
        Core.inRange(hsv, greenLower, greenUpper, greenMask);

        int numRed = Core.countNonZero(redMask);
        int numBlue = Core.countNonZero(blueMask);
        int numGreen = Core.countNonZero(greenMask);

        if (numRed >= numBlue && numRed >= numGreen) color = "RED";
        else if(numBlue>numRed && numBlue>numGreen) color = "BLUE";
        else color = "GREEN";
        System.out.println(color);
    }

    public Mat getOutput() {return output;}
    public String getColor() {return color;}

}

