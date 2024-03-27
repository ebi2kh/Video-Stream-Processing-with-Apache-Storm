import org.apache.storm.topology.TopologyBuilder;
import org.opencv.core.Core;


public class MainVideoProcessor {
    static {
        // Load the native OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) throws Exception {
        // Create an instance of VideoProcessing class
//        VideoProcessing videoProcessing = new VideoProcessing();
        Preprocessing.VideoProcess videoProcessing = new Preprocessing.VideoProcess ();

        // Process the video using custom logic (implementation not provided)
        videoProcessing.processVideo();

        // Create an instance of StormTopology class
        Topology.ImageToplogy stormTopology = new Topology.ImageToplogy();

        // Run the Apache Storm topology for real-time image processing
        stormTopology.runStormTopology();
    }

}


