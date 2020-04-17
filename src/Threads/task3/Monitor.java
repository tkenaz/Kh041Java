package Threads.task3;

import java.util.List;

public class Monitor {

    private String fileName;
    public static final String STOPWORD = "Abort operation";

    public synchronized void setFileName(String fileName) {
        if(fileName == null) {
            throw new IllegalArgumentException();
        }
        this.fileName = fileName;
        Monitor.this.notifyAll();
    }
/**
 * if fileName returns null
 * this means the end of work*/

    public synchronized String getFileName() {
        while (fileName == null) {
            try {
                Monitor.this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String temporaryFileName = fileName;
        fileName = null;
        return temporaryFileName;
    }

    public void setStatus(String status) {

    }
/**
 * return String status
 * if status is null
 * that means that the process is
 * completed and result is ready*/

    public String getStatus() {
        return null;
    }

    public void setResult(List<Integer> results){

    }

    public List<Integer> getResult(){
        return null;
    }

    public synchronized void abortThread(){
        fileName = STOPWORD;
        notifyAll();
    }
}
