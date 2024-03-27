//package Combiner;
//
//import org.apache.storm.task.OutputCollector;
//import org.apache.storm.task.TopologyContext;
//import org.apache.storm.topology.OutputFieldsDeclarer;
//import org.apache.storm.topology.base.BaseRichBolt;
//import org.apache.storm.tuple.Fields;
//import org.apache.storm.tuple.Tuple;
//import org.apache.storm.tuple.Values;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.imgcodecs.Imgcodecs;
//
//import java.util.Map;
//import java.util.HashMap;
//
//public class FrameCombinerBolt extends BaseRichBolt {
//
//    private OutputCollector outputCollector;
//    private Map<Integer, Mat> blurFrameMap = new HashMap<>();
//    private Map<Integer, Mat> sharpenFrameMap = new HashMap<>();
//    private int currentIndex = 0;
//
//    @Override
//    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
//        this.outputCollector = collector;
//    }
//
//    @Override
//    public void execute(Tuple tuple) {
//        try {
//            Combine comb = new Combine();
//            comb.cal(tuple);
////            String processingTag = tuple.getStringByField("tag");
////            int index = tuple.getIntegerByField("index");
////            Mat frame = (Mat) tuple.getValueByField("frame");
////
////            if (processingTag.equals("GaussianBlur")) {
////                blurFrameMap.put(index, frame);
////            } else if (processingTag.equals("Sharpening")) {
////                sharpenFrameMap.put(index, frame);
////            }
////
////            if (blurFrameMap.containsKey(currentIndex) && sharpenFrameMap.containsKey(currentIndex)) {
////                Mat blurFrame = blurFrameMap.get(currentIndex);
////                Mat sharpenFrame = sharpenFrameMap.get(currentIndex);
////
////                Mat combinedFrame = new Mat();
////                Core.addWeighted(blurFrame, 0.5, sharpenFrame, 0.5, 0, combinedFrame);
////
////                blurFrameMap.remove(currentIndex);
////                sharpenFrameMap.remove(currentIndex);
////
////                currentIndex++;
////                String frameName = String.valueOf(currentIndex);
////                outputCollector.emit(new Values(combinedFrame.clone(), frameName));
////                System.out.println("FrameCombinerBolt processed frame " + currentIndex);
////            }
//        } catch (Exception e) {
//            System.err.println("Error in FrameCombinerBolt: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void declareOutputFields(OutputFieldsDeclarer declarer) {
//        declarer.declare(new Fields("combined-frame" , "index"));
//    }
//}
//-------------------------------------------------

package Combiner;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.Map;
import java.util.HashMap;

public class FrameCombinerBolt extends BaseRichBolt {

    private OutputCollector outputCollector;
    private FrameMap blurFrameMap;
    private FrameMap sharpenFrameMap;
    private int currentIndex;

    @Override
    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        this.outputCollector = collector;
        this.blurFrameMap = new FrameMap();
        this.sharpenFrameMap = new FrameMap();
        this.currentIndex = 0;
    }

    @Override
    public void execute(Tuple tuple) {
        try {
            String processingTag = tuple.getStringByField("tag");
            int index = tuple.getIntegerByField("index");
            int frameL = tuple.getIntegerByField("frameL");
            Mat frame = (Mat) tuple.getValueByField("frame");

            if (processingTag.equals("GaussianBlur")) {
                blurFrameMap.addFrame(index, frame);
            } else if (processingTag.equals("Sharpening")) {
                sharpenFrameMap.addFrame(index, frame);
            }

            if (blurFrameMap.containsFrame(currentIndex) && sharpenFrameMap.containsFrame(currentIndex)) {
                Mat blurFrame = blurFrameMap.getFrame(currentIndex);
                Mat sharpenFrame = sharpenFrameMap.getFrame(currentIndex);

                Mat combinedFrame = new Mat();
                Core.addWeighted(blurFrame, 0.5, sharpenFrame, 0.5, 0, combinedFrame);

                blurFrameMap.removeFrame(currentIndex);
                sharpenFrameMap.removeFrame(currentIndex);

                currentIndex++;
                String frameName = String.valueOf(currentIndex);
                outputCollector.emit(new Values(combinedFrame.clone(), frameName , frameL));
                System.out.println("FrameCombinerBolt processed frame " + currentIndex);
            }
        } catch (Exception e) {
            System.err.println("Error in FrameCombinerBolt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("combined-frame" , "index" , "frameL"));
    }
}

