package Preprocessing;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FrameAnalyzer {

    public void frameExtractor() throws IOException {
        VideoCapture cap = new VideoCapture();
        String input = "Files/sample.mp4";
        cap.open(input);
        Mat frame_ = new Mat();
        int frameNumber = 0;
        Path outputFolderPath = Paths.get("Files/Orginal_frame");
        Files.createDirectories(outputFolderPath);
        while (cap.read(frame_)) {
            System.out.println("Frame Obtained "+ frameNumber);
            System.out.println("Captured Frame Width " + frame_.width() + " Height " + frame_.height());
            Imgcodecs.imwrite("Files/Orginal_frame/frame_" + frameNumber + ".jpg", frame_);
            frameNumber++;
        }

        if (frameNumber == 0) {
            System.out.println("Could not open the video.");
        }

        cap.release();


    }
    public Mat convertToGray(Mat frame) {
        Mat grayFrame = new Mat();
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
        return grayFrame;
    }

    public Mat resizeFrame(Mat frame, Size size) {
        Mat resizedFrame = new Mat();
        Imgproc.resize(frame, resizedFrame, size);
        return resizedFrame;
    }

    public Scalar calculateAverageBrightness(Mat frame) {
        return Core.mean(frame);
    }


}
