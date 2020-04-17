package Threads;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*Task1 t1 = new Task1(); // ToDo by MV add millis
        //System.out.println(t1.primesSingleList());
        System.out.println(t1.primesMultipleLists());*/

        Task2 t2 = new Task2();
        //System.out.println(t2.primesSingleList());
       // System.out.println(t2.primesMultipleLists());
        System.out.println(Runtime.getRuntime().availableProcessors());


    }
}
