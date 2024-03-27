package Topology;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class ImageToplogy {


        // Method to run the Apache Storm topology for distributed image processing
        public void runStormTopology() throws Exception {
            // Build Topology
            TopologyBuilder builder = new TopologyBuilder();

            // Define the spout to read frames from the video
            builder.setSpout("frame-spout", new Spout.ImageFrameSpout(), 1);

            // Define bolts for Gaussian blur and sharpening operations
            builder.setBolt("gaussian-blur-bolt", new GaussianBlur.GaussianBlurBolt(), 1).shuffleGrouping("frame-spout");
            builder.setBolt("sharpening-bolt", new Sharpening.SharpeningBolt(), 1).shuffleGrouping("frame-spout");

            // Define a bolt to combine the output of Gaussian blur and sharpening bolts
            builder.setBolt("combine-bolt", new Combiner.FrameCombinerBolt(), 1)
                    .shuffleGrouping("gaussian-blur-bolt")
                    .shuffleGrouping("sharpening-bolt");

            // Define a bolt to handle the final output
            builder.setBolt("output-bolt", new Output.OutputerBolt(), 1).shuffleGrouping("combine-bolt");

            // Configuration
            Config conf = new Config();
//        conf.setDebug(true);

            // Run Topology Locally using a LocalCluster
            LocalCluster cluster = new LocalCluster();
            try {
                // Submit the topology and let it run for 10 seconds (adjust as needed)
                cluster.submitTopology("video-processing-topology", conf, builder.createTopology());
                Thread.sleep(50000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Shutdown the cluster after processing
                cluster.shutdown();
            }
        }
    }







