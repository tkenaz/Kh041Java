package Threads.task3;

import com.sun.tools.javac.util.ArrayUtils;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChildThread {

    private Monitor monitor;
    private byte[] bytes;

    public ChildThread(Monitor monitor) {
        this.monitor = monitor;
        new Thread(() -> {

                launch();

        }).start();
    }

    private void launch() {
        String fileName;

        while (!Monitor.STOPWORD.equals(fileName = monitor.getFileName())) {

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
    private List<Byte> getContent() {

        return null;
    }
    private List<Integer> findDuplicates() {

        List<Integer> result = new ArrayList<>();
        byte[] tempArray = new byte[bytes.length/2];

        int startIndex = 0;
        int endIndex = tempArray.length -1;
        int step = bytes.length - tempArray.length * 2;

        int matchStartIndex = bytes[0] + tempArray.length - 1;

        System.arraycopy(bytes, startIndex, tempArray, endIndex, tempArray.length);

        // checking the temp array against potentially matching region in bytes
        for (int i = 0; i < tempArray.length; i++) {
            for (int j = matchStartIndex; j <= step; j++) {
                if (tempArray[i] == bytes[j]) {
                    i++;
                    j++;
                } else {
                    matchStartIndex++;
                }
            }
            step += 2;
        }
        return result;
    }
}
