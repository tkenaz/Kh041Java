package Annotations;

/*
Создать свою аннотацию.
При запуске приложения создать по одному экземпляру класса, помеченному вашей аннотацией.
Засетать поля классов из файла конфигурации. Любое значение, которое надо достать.*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Bird {
    private static final String BASE_NAME = "resources_fr.properties";

    public void getTranslation(String name) throws IOException {
        Properties prop = readPropertiesFile();

        System.out.println("translation for the bird " + name + " is: " + prop.getProperty(name));
        if (prop.getProperty(name) == null) {
            System.out.println("There is no translation for the bird " + name);
        }
    }

    public static Properties readPropertiesFile() throws IOException {
        FileInputStream inputStream = null;
        Properties properties = null;
        try {
            inputStream = new FileInputStream(BASE_NAME);
            properties = new Properties();
            properties.load(inputStream);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            inputStream.close();
        }
        return properties;
    }
}
