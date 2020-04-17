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
import java.util.Scanner;

public class Task3 {

    private byte[] content;

    private byte[] getContent() throws IOException {

        Scanner scan = new Scanner(System.in);
        System.out.println("Name of the source file: ");
        String fileName = scan.next();

        content = Files.readAllBytes(Paths.get(fileName));
        return content;
    }


    static int findLongestRepeatingSubSeq(String str)
    {
        int n = str.length();

        // Create and initialize DP table
        int[][] dp = new int[n+1][n+1];

        // Fill dp table (similar to LCS loops)
        for (int i=1; i<=n; i++)
        {
            for (int j=1; j<=n; j++)
            {
                // If characters match and indexes are not same
                if (str.charAt(i-1) == str.charAt(j-1) && i!=j)
                    dp[i][j] =  1 + dp[i-1][j-1];

                    // If characters do not match
                else
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        return dp[n][n];
    }

    public static void main (String[] args)
    {
        String str = "abcfwc01ab abcfwc01 xb";


        System.out.println("The length of the largest subsequence that"
                +" repeats itself is : "+findLongestRepeatingSubSeq(str));


    }
}
