package Annotations;

import java.io.IOException;

@Translated
public class Duck extends Bird {

    private static String name = "duck";

    public static void main(String[] args) throws IOException {
        Bird duck = new Duck();
        duck.getTranslation(name);
    }
}
