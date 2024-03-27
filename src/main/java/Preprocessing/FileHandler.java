package Preprocessing;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class FileHandler {

    public void clearExistingOutputFolder() throws IOException {
        Path outputFolderPath = Paths.get("Files/Gray_Resize_frames");
        if (Files.exists(outputFolderPath)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(outputFolderPath)) {
                for (Path filePath : directoryStream) {
                    Files.delete(filePath);
                }
            }
            Files.delete(outputFolderPath);
        }
    }

    public void createOutputFolder() throws IOException {
        Path outputFolderPath = Paths.get("Files/Gray_Resize_frames");
        Files.createDirectories(outputFolderPath);
    }

    public PrintWriter createAnalysisWriter(String filePath) throws IOException {
        return new PrintWriter(new FileWriter(filePath));
    }

    public void saveProcessedFrame(Mat frame, int frameCount, String outputFolder) {
        String framePath = outputFolder + "/frame_" + frameCount + ".jpg";
        Imgcodecs.imwrite(framePath, frame);
    }

    public void writeBrightnessToAnalysisFile(PrintWriter writer, int frameCount, Scalar avgBrightness) {
        writer.println("Frame " + frameCount + ": " + avgBrightness.val[0]);
    }

    public void writeOverallBrightnessToAnalysisFile(PrintWriter writer, int frameCount, double averageBrightness) {
        writer.println("\n" + "********************************************" + "\n\n" +
                "Total frames: " + frameCount + "\n\n" +
                "**************************************************" + "\n");
        writer.println("Average brightness: " + averageBrightness + "\n");
    }

    public void closeAnalysisWriter(PrintWriter writer) {
        writer.close();
    }

    public void displayStatistics(int frameCount, double averageBrightness) {
        System.out.println("Total frames: " + frameCount);
        System.out.println("Average brightness: " + averageBrightness);
    }
}
