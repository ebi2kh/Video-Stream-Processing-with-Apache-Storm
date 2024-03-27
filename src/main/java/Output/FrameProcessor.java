package Output;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FrameProcessor {

    public static void processFrame(Mat frame, String outputFolderPath, String originalFileName, int frameL) {
        try {
            if (frame.empty()) {
                System.err.println("Error: Frame could not be read.");
                return;
            }

            writeFrameToFile(frame, outputFolderPath, originalFileName);

            if (Integer.valueOf(originalFileName) == frameL) {
                String inputFolderPath = outputFolderPath;
                String outputVideoPath = "GeneratedVideo.mp4";
                int frameRate = 25;
                Size frameSize = new Size(640, 360);

                File dir = new File(inputFolderPath);
                File[] directoryListing = dir.listFiles();

                if (directoryListing != null) {
                    Arrays.sort(directoryListing, new Comparator<File>() {
                        @Override
                        public int compare(File f1, File f2) {
                            int num1 = extractFrameNumber(f1.getName());
                            int num2 = extractFrameNumber(f2.getName());
                            return num1 - num2;
                        }

                        private int extractFrameNumber(String name) {
                            int index = 0;
                            try {
                                int start = name.indexOf('_') + 1;
                                int end = name.lastIndexOf('.');
                                String number = name.substring(start, end);
                                number = number.replace(".jpg", "");
                                index = Integer.parseInt(number);
                            } catch (Exception e) {
                                index = 0;
                            }
                            return index;
                        }
                    });

                    VideoWriterHelper.writeVideo(outputVideoPath, frameRate, frameSize, directoryListing);
                } else {
                    System.out.println("Directory not found or is not a directory");
                }
            }

        } catch (Exception e) {
            handleException(e);
        }
    }

    private static void writeFrameToFile(Mat frame, String outputFolderPath, String originalFileName) {
        String framePath = outputFolderPath + File.separator + "frame_" + originalFileName + ".jpg";
        Imgcodecs.imwrite(framePath, frame);
    }

    private static void handleException(Exception e) {
        System.err.println("Error in FrameProcessor: " + e.getMessage());
        e.printStackTrace();
    }
}
