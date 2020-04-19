package Annotations;

import java.io.IOException;

@Translated
public class Sparrow extends Bird {

    private static String name = "sparrow";

    public static void main(String[] args) throws IOException {
        Bird sparrow = new Sparrow();
        sparrow.getTranslation(name);
    }
}
