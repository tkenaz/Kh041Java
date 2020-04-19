package Threads;
/*
* 3.	Написать программу, которая будет находить самую длинную повторяющуюся последовательность байт в файле.
    a.	Имя файла вводить с клавиатуры.
    b.	Файл можно считать в память целиком.
    c.	Поиск последовательности надо реализовать в отдельном потоке выполнения.

        i.	Поток должен запускаться при старте программы
        ii.	После указания имени файла главный поток должен оповестить дополнительный о том, что он может начать поиск.
        iii.	Дополнительный поток должен предоставлять информацию о ходе поиска, а главный – выводить эту информацию
            на экран. Например, можно печатать текущее значение длины последовательности.
        iv.	После завершения поиска главный поток должен вывести число байт в самой длинной повторяющейся последовательности
            и индексы первого и второго ее вхождения.
        v.	После завершения поиска вспомогательный поток должен перейти в состояние ожидания, а главный – запросить
            имя следующего файла.

*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Task3 {

    private static byte[] bytes;
    private static String source;


    private static String getContent() throws IOException {

        /*Scanner scan = new Scanner(System.in);
        System.out.println("Name of the source file: ");
        String fileName = scan.next();*/

        bytes = Files.readAllBytes(Paths.get("sourceFile.txt"));
        String source = new String(bytes);
        return source;
    }

    private static void findDuplicates() throws IOException {

        Set<Integer> result = new TreeSet<>();

        source = getContent();

        String regionSource = source.substring(0, source.length() / 2);

        int step = source.length() - regionSource.length() * 2;
        int sourceStartIndex = 0;
        int remainderStartIndex = regionSource.length();
        int regionStartIndex = 0;
        boolean flag = false;



        while (source.length() > 0) {

            if (regionSource.length() == 0 || flag) {
                source = source.substring(sourceStartIndex + 1);
                regionSource = source.substring(0, source.length() / 2);
                flag = false;

            } else if (regionSource.regionMatches(regionStartIndex, source, remainderStartIndex, regionSource.length())) {
                result.add(regionSource.length());
                flag = true;
            } else {
                regionSource = regionSource.substring(regionStartIndex, regionSource.length() - 1);
                step += 2;
            }
        }

        System.out.println(result);
        System.out.println(source);
        System.out.println(source.length());
        System.out.println(regionSource);
        System.out.println("step: " + step);
        System.out.println("remainderStartIndex: " + remainderStartIndex);


    }

    public static void main(String[] args) throws IOException {
        findDuplicates();
    }
}
