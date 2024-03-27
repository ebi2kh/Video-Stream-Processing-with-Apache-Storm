package Spout;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

class FileSorter {
    static void sortFiles(File[] files) {
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                int num1 = extractNumber(file1.getName());
                int num2 = extractNumber(file2.getName());
                return num1 - num2;
            }

            private int extractNumber(String name) {
                int index = 0;
                try {
                    int start = name.indexOf('_') + 1;
                    int end = name.lastIndexOf('.');
                    String number = name.substring(start, end);
                    index = Integer.parseInt(number);
                } catch (Exception e) {
                    index = 0;
                }
                return index;
            }
        });
    }
}
