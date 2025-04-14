package utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public static void writeToFile(String filename, String content) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(content + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }
}
