package com.skowron.Rekrutacja.utils;

import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonFileWriter {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String DEFAULT_OUTPUT_DIR = "savedFiles";

    public static <T> void saveObjectToJsonFile(T object, String fileName) {
        saveObjectToJsonFile(object, fileName, DEFAULT_OUTPUT_DIR);
    }

    public static <T> void saveObjectToJsonFile(T object, String fileName, String path) {
        Path outputDir = Path.of(path);

        try {
            Files.createDirectories(outputDir);
        } catch (IOException e) {
            throw new RuntimeException("Nie można utworzyć katalogu: " + outputDir, e);
        }

        Path filePath = outputDir.resolve(fileName);

        String json = objectMapper.writeValueAsString(object);

        try {
            Files.writeString(filePath, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
