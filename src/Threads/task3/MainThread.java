package Threads.task3;

import java.util.Scanner;

public class MainThread {

    public void launch() {
        Monitor monitor = new Monitor();
        new ChildThread(monitor);

        while(true) {
            String fileName = readFileName();
            if (Monitor.STOPWORD.equals(fileName)) {
                monitor.abortThread();
                break;
            }

            monitor.setFileName(fileName);

            String status;
            while ((status = monitor.getStatus()) != null) {
                System.out.println(Thread.currentThread().getName() + ": Current status is: " + status);
            }

            System.out.println(Thread.currentThread().getName() + ": The result is: " + monitor.getResult());
        }
    }

    private String readFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Thread.currentThread().getName() + ": Please input fileName: ");
        String fileName = scanner.nextLine();
        scanner.close(); // ToDo by MV check scanner close
        return fileName;
    }



}
