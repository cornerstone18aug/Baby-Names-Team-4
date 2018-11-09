import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    public static String[] readLinesFromFile(String fileName) {
        List<String> lines = null;
        try {
            Charset charset = Charset.forName("UTF-8");
            lines = Files.readAllLines(Paths.get(fileName), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.toArray(new String[0]);
    }

    public static void writeToFile(String fileName, String text) {
        try {
            Files.write(Paths.get(fileName), text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] getFilesList(String dir) {
        ArrayList<String> strList = new ArrayList<String>();
        File directory = new File(dir);
        File[] list = directory.listFiles();
        for (File file : list) {
            strList.add(file.getPath());
        }
        return strList.toArray(new String[0]);
    }
}
