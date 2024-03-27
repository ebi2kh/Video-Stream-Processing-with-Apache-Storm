package Spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class ImageFrameSpout extends BaseRichSpout {

    private SpoutOutputCollector outputCollector;
    private File[] imageFiles;
    private int currentImageIndex;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.outputCollector = collector;
        File directory = new File("Files/Gray_Resize_frames");
        this.imageFiles = directory.listFiles();
        FileSorter.sortFiles(this.imageFiles);
        this.currentImageIndex = 0;
    }

    @Override
    public void nextTuple() {
        try {
            if (currentImageIndex < imageFiles.length) {
                Mat image = Imgcodecs.imread(imageFiles[currentImageIndex].getPath());
                if (!image.empty()) {
                    outputCollector.emit(new Values(image.clone(), imageFiles.length));
                    System.out.println("ImageFrameSpout emitted frame " + currentImageIndex);
                    currentImageIndex++;
                }
            }
        } catch (Exception e) {
            System.err.println("Error in ImageFrameSpout: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("frame", "frameL"));
    }
}

