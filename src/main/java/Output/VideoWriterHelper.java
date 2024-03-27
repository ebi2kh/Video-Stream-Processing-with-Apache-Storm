package Output;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoWriter;

import java.io.File;

public class VideoWriterHelper {

    public static void writeVideo(String outputVideoPath, int frameRate, Size frameSize, File[] directoryListing) {
        try {
            VideoWriter writer = new VideoWriter(outputVideoPath, VideoWriter.fourcc('H', '2', '6', '4'), frameRate, frameSize, true);

            for (File file : directoryListing) {
                Mat image = Imgcodecs.imread(file.getAbsolutePath());
                if (!image.empty()) {
                    writer.write(image);
                }
            }

            writer.release();
        } catch (Exception e) {
            handleException(e);
        }
    }

    private static void handleException(Exception e) {
        System.err.println("Error in VideoWriterHelper: " + e.getMessage());
        e.printStackTrace();
    }
}
