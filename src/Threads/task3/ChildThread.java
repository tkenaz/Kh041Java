package Threads.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ChildThread {

    private Monitor monitor;

    public ChildThread(Monitor monitor) {
        this.monitor = monitor;
        new Thread(() -> {

                launch();

        }).start();
    }

    private void launch() {
        String fileName;

        while (!Monitor.STOPWORD.equals(fileName = monitor.getFileName())) {
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(Paths.get(fileName));
            } catch (IOException e) {
                System.out.println(Thread.currentThread().getName() + " ERROR: " + e.getMessage());
                monitor.setStatus(": Impossible to read the file");
                monitor.setResult(null); // ToDo by MV add 0 to the list
                continue;
            }

            //findDuplicates();

            //monitor.setResult();

        }
    }
}
