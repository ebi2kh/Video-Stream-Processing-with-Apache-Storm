package GaussianBlur;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class FrameProcessor {

    public void processFrame(Tuple tuple, OutputCollector collector, int frameIndex) {
        try {
            Mat frame = (Mat) tuple.getValueByField("frame");
//            Integer frameL =  tuple.getValueByField("frameL");
            int frameL = tuple.getIntegerByField("frameL");
            Mat blurredFrame = applyGaussianBlur(frame);

            // Emit the processed frame with additional information
            collector.emit(new Values("GaussianBlur", frameIndex, blurredFrame, frameL));
        } catch (Exception e) {
            System.err.println("Error in FrameProcessor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Mat applyGaussianBlur(Mat frame) {
        Mat blurredFrame = new Mat();
        Imgproc.GaussianBlur(frame, blurredFrame, new Size(45, 45), 0);
        return blurredFrame;
    }
}
