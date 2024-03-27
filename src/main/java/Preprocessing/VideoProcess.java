package Preprocessing;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;

import java.io.IOException;
import java.io.PrintWriter;

public class VideoProcess {

    private static final String INPUT_VIDEO_PATH = "Files/sample.mp4";
    private static final String OUTPUT_FRAME_FOLDER = "Files/Gray_Resize_frames";
    private static final String ANALYSIS_FILE_PATH = "Files/frame_analysis.txt";
    private static final String ORGINAL_FILE_PATH = "Files/frame_analysis.txt";
    private static final Size RESIZED_FRAME_SIZE = new Size(640, 360);

    private FileHandler fileHandler = new FileHandler();
    private FrameAnalyzer frameAnalyzer = new FrameAnalyzer();

    public void processVideo() throws IOException {
        VideoCapture videoCapture = new VideoCapture(INPUT_VIDEO_PATH);
        Mat frame = new Mat();
        int frameCount = 0;
        double totalBrightness = 0;

        fileHandler.clearExistingOutputFolder();

        fileHandler.createOutputFolder();

        PrintWriter analysisWriter = fileHandler.createAnalysisWriter(ANALYSIS_FILE_PATH);
        FrameAnalyzer an = new FrameAnalyzer();
        an.frameExtractor();
        while (videoCapture.read(frame)) {

            Mat grayFrame = frameAnalyzer.convertToGray(frame);
            Mat resizedFrame = frameAnalyzer.resizeFrame(grayFrame, RESIZED_FRAME_SIZE);

            fileHandler.saveProcessedFrame(resizedFrame, frameCount, OUTPUT_FRAME_FOLDER);

            Scalar avgBrightness = frameAnalyzer.calculateAverageBrightness(resizedFrame);
            totalBrightness += avgBrightness.val[0];

            fileHandler.writeBrightnessToAnalysisFile(analysisWriter, frameCount, avgBrightness);

            frameCount++;
        }

        double averageBrightness = totalBrightness / frameCount;
        fileHandler.writeOverallBrightnessToAnalysisFile(analysisWriter, frameCount, averageBrightness);

        fileHandler.closeAnalysisWriter(analysisWriter);

        fileHandler.displayStatistics(frameCount, averageBrightness);
    }
}
