package Threads.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChildThread {

    private Monitor monitor;
    private static byte[] bytes;
    private static String source;

    public ChildThread(Monitor monitor) {
        this.monitor = monitor;
        new Thread(() -> {

                launch();

        }).start();
    }

    private void launch() {
        String fileName;

        while (!Monitor.STOP.equals(fileName = monitor.getFileName())) {

            try {
                bytes = Files.readAllBytes(Paths.get(fileName));
            } catch (IOException e) {
                System.out.println(Thread.currentThread().getName() + " ERROR: " + e.getMessage());
                monitor.setStatus(": Impossible to read the file");
                monitor.setResult(null);
                continue;
            }

            //findDuplicates();

            //monitor.setResult();

        }
    }
    private String getContent() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Name of the source file: ");
        String fileName = scan.next();

        bytes = Files.readAllBytes(Paths.get("sourceFile.txt"));
        String source = new String(bytes);
        return source;
    }
    private List<Integer> findDuplicates() {

        List<Integer> result = new ArrayList<>();

        try {
            source = getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String regionSource;

        int sourceStartIndex = 0;

        int regionStartIndex = 0;


        while (source.length() > 0) {
            regionSource = source.substring(0, source.length() / 2);
            while (regionSource.length() > 1) {

                int remainderStartIndex = regionSource.length();

                while (remainderStartIndex >= source.length() - regionSource.length() || remainderStartIndex < source.length()) {
                    if (regionSource.regionMatches(regionStartIndex, source, remainderStartIndex, regionSource.length())) {
                        result.add(regionSource.length());
                        result.add(regionStartIndex);
                        result.add(remainderStartIndex);

                        remainderStartIndex++;
                    }
                }
                regionSource = regionSource.substring(regionStartIndex, regionSource.length() - 1);
            }
            source = source.substring(sourceStartIndex + 1);
        }
        return result;
    }
}
