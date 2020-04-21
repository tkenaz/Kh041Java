package Threads.task3;

import java.util.List;

public class Monitor {

    private String fileName;
    private String status;
    private List<Integer> results;
    public static final String STOP = "Abort operation";

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
        this.status = status;
        Monitor.this.notifyAll();
    }
/**
 * return String status,
 * if status is null that means that the process is completed and result is ready.
 * The status is returned after s certain iteration withing the child thread.
 * */

    public String getStatus() {
        while (status == null) {
            try {
                Monitor.this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String temporaryStatus = status;
        status = null;
        return temporaryStatus;
    }

    public void setResult(List<Integer> results){
        this.results = results;
        Monitor.this.notifyAll();
    }

    public List<Integer> getResult(){
        while (results.isEmpty()) {
            try {
                Monitor.this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return results;
    }

    public synchronized void abortThread(){
        fileName = STOP;
        notifyAll();
    }
}
