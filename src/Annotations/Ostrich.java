package Annotations;

import java.io.IOException;

@Translated
public class Ostrich {

    private static String name = "ostrich";

    public static void main(String[] args) throws IOException {
        Bird sparrow = new Sparrow();
        sparrow.getTranslation(name);
    }
}
