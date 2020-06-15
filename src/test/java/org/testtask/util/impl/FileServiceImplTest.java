package org.testtask.util.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testtask.util.FileService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileServiceImplTest {
    private static final String PATH_TO_TEST_READFILE = "src/test/resources/testFileRead.txt";
    private static final String WRONG_PATH_TO_TEST_READFILE = "src/test/resources/wrongTestFileRead.txt";
    private static final String PATH_TO_TEST_WRITEFILE = "src/test/resources/testFileWrite.txt";
    private static final String SPEEDELEC_STRING = "SPEEDELEC E-Scooter; 60; 15300; false; 14800; marine; 309";
    private static final String EBIKE_STRING = "E-BIKE Lankeleisi; 65; 24200; false; 10000; black; 2399";
    private static final String FOLDINGBIKE_STRING = "FOLDING BIKE Benetti; 24; 27; 11400; false; rose; 1009";

    FileService fileService;
    List<String> bikesString;

    @BeforeEach
    private void init() {
        fileService = new FileServiceImpl();
        bikesString = new ArrayList<>();
        bikesString.add(SPEEDELEC_STRING);
        bikesString.add(EBIKE_STRING);
        bikesString.add(FOLDINGBIKE_STRING);
    }

    @Test
    void readFromFileToList() throws IOException {
        assertEquals(bikesString, fileService.readFromFileToList(PATH_TO_TEST_READFILE));
        assertThrows(IOException.class, () -> fileService.readFromFileToList(WRONG_PATH_TO_TEST_READFILE));
    }

    @Test
    void writeToFile() {
        assertEquals(true, fileService.writeToFile(bikesString, PATH_TO_TEST_WRITEFILE));
    }

    @AfterEach
    private void destroy() throws IOException {
        Files.delete(Paths.get(PATH_TO_TEST_WRITEFILE));
    }
}
