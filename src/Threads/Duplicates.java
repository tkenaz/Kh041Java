package Threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Duplicates {
    private static byte[] bytes;
    private static String source;


    private static byte[] getContent() throws IOException {

        /*Scanner scan = new Scanner(System.in);
        System.out.println("Name of the source file: ");
        String fileName = scan.next();*/

        bytes = Files.readAllBytes(Paths.get("sourceFile.txt"));

        return bytes;
    }

    public static List<Integer> findDuplicates() throws IOException {
        getContent();
        List<Integer> result = new ArrayList<>();

        int n = bytes.length;

        // Create and initialize DP table
        int[][] dp = new int[n+1][n+1];

        // Fill dp table (similar to LCS loops)
        for (int i=1; i<=n; i++)
        {
            for (int j=1; j<=n; j++)
            {
                // If characters match and indexes are not same
                if (bytes[i-1] == bytes[j-1] && i!=j)
                    dp[i][j] =  1 + dp[i-1][j-1];

                    // If characters do not match
                else
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }

        System.out.println(Arrays.deepToString(dp));
        System.out.println(dp[n][n]);

        return result;
    }

    public static void main(String[] args) throws IOException{
        System.out.println(getContent().toString());
        findDuplicates();
    }
}
