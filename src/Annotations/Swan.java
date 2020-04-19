package Annotations;

import java.io.IOException;

@Translated
public class Swan {

    private static String name = "swan";

    public static void main(String[] args) throws IOException {
        Bird sparrow = new Sparrow();
        sparrow.getTranslation(name);
    }
}
