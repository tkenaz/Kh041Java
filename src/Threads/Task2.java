package Threads;
/*
* 1.	С использованием нескольких потоков выполнения (Thread) реализовать поиск простых чисел внутри заданного диапазона.
    a.	Ввести с клавиатуры интервал поиска чисел (например, от 1 до 10000) и число потоков.
    b.	Подумать, как разделять интервал поиска по потокам.
    c.	Проверку на простоту реализовать самым простым способом: циклом от 2 до числа/2.
    d.	Найденные числа должны быть записаны в одну общую коллекцию.

        i.	Проверить, что будет быстрее, сохранять числа в общую коллекцию сразу по их нахождению.
        ii.	Или сохранять найденные числа в отдельные коллекции каждого потока, а потом их содержимое добавлять в общую коллекцию.

2.	Сделать такую же реализацию с использованием Executor’ов
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task2 {

    public List<Integer> primesSingleList() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Set threads quantity: ");
        int threadsNumber = scan.nextInt();

        System.out.println("Set interval from: ");
        int startNum = scan.nextInt();

        System.out.println("Set interval to: ");
        int endNum = scan.nextInt() + 1;

        int blockSize = (int) (Math.ceil((endNum - startNum) / (double) threadsNumber));

        List<Integer> primeList = Collections.synchronizedList(new ArrayList<>()); //ToDo by MV: find sync collection

        ExecutorService service = Executors.newFixedThreadPool(threadsNumber);

        for (int i = 0; i < threadsNumber; i++) {
            int blockStart = startNum + i * blockSize;
            service.submit(() -> IntStream.range(blockStart, Math.min(endNum, blockStart + blockSize))
                    .filter(Task1::isPrime)
                    .forEach(primeList::add));

        }

        service.shutdown();
        if (!service.awaitTermination(10, TimeUnit.SECONDS)) {
            throw new RuntimeException("Timeout occurred.");
        }

        return primeList;
    }

    public List<Integer> primesMultipleLists() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Set threads quantity: ");
        int threadsNumber = scan.nextInt();

        System.out.println("Set interval from: ");
        int startNum = scan.nextInt();

        System.out.println("Set interval to: ");
        int endNum = scan.nextInt() + 1;

        int blockSize = (int) (Math.ceil((endNum - startNum) / (double) threadsNumber));

        List<Integer> primeList = Collections.synchronizedList(new ArrayList<>()); //ToDo by MV: find sync collection

        ExecutorService service = Executors.newFixedThreadPool(threadsNumber);

        for (int i = 0; i < threadsNumber; i++) {
            int blockStart = startNum + i * blockSize;
            service.submit(() -> {
                List<Integer> subList = IntStream.range(blockStart, Math.min(endNum, blockStart + blockSize))
                        .filter(Task1::isPrime)
                        .boxed()
                        .collect(Collectors.toList());
                primeList.addAll(subList);
            });
        }

        service.shutdown();
        if (!service.awaitTermination(10, TimeUnit.SECONDS)) {
            throw new RuntimeException("Timeout occurred.");
        }

        return primeList;
    }
}
