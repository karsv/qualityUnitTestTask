package org.testtask.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.testtask.service.FileService;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LogManager.getLogger(FileServiceImpl.class);

    public List<String> readFromFileToList(String pathToFile) throws IOException {
        return Files.readAllLines(Path.of(pathToFile));
    }

    public boolean writeToFile(List<String> bikes, String path) {
        try {
            Files.write(Path.of(path), bikes);
            return true;
        } catch (IOException e) {
            System.out.println("Error while writing to file: " + path);
            return false;
        }
    }
}
