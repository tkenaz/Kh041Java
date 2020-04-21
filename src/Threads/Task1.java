package Threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task1 extends Thread {

    public static boolean isPrime(int toTest) {
        double root = Math.sqrt(toTest);

        if (toTest <= 1) return false;
        for (int n = 2; n <= root; n++) {
            if (toTest % n == 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> primesSingleList() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Set threads quantity: ");
        int threadsNumber = scan.nextInt();

        System.out.println("Set interval from: ");
        int startNum = scan.nextInt();

        System.out.println("Set interval to: ");
        int endNum = scan.nextInt() + 1;

        int blockSize = (int) (Math.ceil((endNum - startNum) / (double) threadsNumber));

        List<Thread> threadsList = new ArrayList<>();
        List<Integer> primeList = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < threadsNumber; i++) {
            int blockStart = startNum + i * blockSize;
            Thread t = new Thread(() -> IntStream.range(blockStart, Math.min(endNum, blockStart + blockSize))
                    .filter(Task1::isPrime)
                    .forEach(primeList::add));
            t.start();
            threadsList.add(t);
        }
        threadsList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return primeList;
    }

    public List<Integer> primesMultipleLists() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Set threads quantity: ");
        int threadsNumber = scan.nextInt();

        System.out.println("Set interval from: ");
        int startNum = scan.nextInt();

        System.out.println("Set interval to: ");
        int endNum = scan.nextInt() + 1;

        int blockSize = (int) (Math.ceil((endNum - startNum) / (double) threadsNumber));

        List<Thread> threadsList = new ArrayList<>();
        List<Integer> primeList = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < threadsNumber; i++) {
            int blockStart = startNum + i * blockSize;
            Thread t = new Thread(() -> {
                List<Integer> subList = IntStream.range(blockStart, Math.min(endNum, blockStart + blockSize))
                        .filter(Task1::isPrime)
                        .boxed()
                        .collect(Collectors.toList());
                primeList.addAll(subList);
            });
            t.start();
            threadsList.add(t);

        }
        threadsList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return primeList;
    }
}
