package org.testtask.util;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<String> readFromFileToList(String pathToFile) throws IOException;

    boolean writeToFile(List<String> bikes, String path);
}
