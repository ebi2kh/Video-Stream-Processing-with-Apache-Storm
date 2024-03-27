package Sharpening;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class SharpeningBolt extends BaseRichBolt {

    private OutputCollector collector;
    private int frameIndex = 0;

    @Override
    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple tuple) {
        try {
            FrameProcessor frameProcessor = new FrameProcessor();
            frameProcessor.processFrame(tuple, collector, frameIndex);
            frameIndex++;
        } catch (Exception e) {
            System.err.println("Error in Sharpening: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("tag", "index", "frame", "frameL"));
    }
}
