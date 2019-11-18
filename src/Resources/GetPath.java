package Resources;

import java.io.File;

public class GetPath {

    public static String getPath() {
        String fileName = "initialize.json";
        File file = new File(fileName);
        return file.getAbsolutePath();
    }
}
