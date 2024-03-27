package Combiner;

import org.opencv.core.Mat;

import java.util.HashMap;
import java.util.Map;

class FrameMap {
    private Map<Integer, Mat> frameMap;

    public FrameMap() {
        this.frameMap = new HashMap<>();
    }

    public void addFrame(int index, Mat frame) {
        frameMap.put(index, frame);
    }

    public boolean containsFrame(int index) {
        return frameMap.containsKey(index);
    }

    public Mat getFrame(int index) {
        return frameMap.get(index);
    }

    public void removeFrame(int index) {
        frameMap.remove(index);
    }
}
