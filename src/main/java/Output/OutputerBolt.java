package Output;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.io.File;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

public class OutputerBolt extends BaseRichBolt {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private OutputCollector collector;
    private Queue<Mat> frameQueue = new LinkedList<>();
    private String outputFolderPath = "Files/output_frames";

    @Override
    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        createOutputFolder();
    }

    private void createOutputFolder() {
        File folder = new File(outputFolderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    @Override
    public void execute(Tuple tuple) {
        try {
            Mat frame = (Mat) tuple.getValue(0);
            String originalFileName = tuple.getStringByField("index");
            int frameL = tuple.getIntegerByField("frameL");

            FrameProcessor.processFrame(frame, outputFolderPath, originalFileName, frameL);
        } catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public void cleanup() {
        // Additional cleanup logic if needed
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        // Declare the output fields if any
    }

    private void handleException(Exception e) {
        System.err.println("Error in OutputBolt: " + e.getMessage());
        e.printStackTrace();
    }
}
