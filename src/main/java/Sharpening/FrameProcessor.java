package Sharpening;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class FrameProcessor {

    public void processFrame(Tuple tuple, OutputCollector collector, int frameIndex) {
        try {
            Mat frame = (Mat) tuple.getValueByField("frame");
            int frameL = tuple.getIntegerByField("frameL");
            Mat sharpenedFrame = applySharpening(frame);
            System.out.println("Sharpening emit frame: "+ frameIndex );
            // Emit the sharpened frame with additional information
            collector.emit(new Values("Sharpening", frameIndex, sharpenedFrame, frameL));
        } catch (Exception e) {
            System.err.println("Error in FrameProcessor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Mat applySharpening(Mat frame) {
        Mat sharpenedFrame = new Mat();
        Imgproc.GaussianBlur(frame, sharpenedFrame, new Size(0, 0), 3);
        Core.addWeighted(frame, 1.5, sharpenedFrame, -0.5, 0, sharpenedFrame);
        return sharpenedFrame;
    }
}
