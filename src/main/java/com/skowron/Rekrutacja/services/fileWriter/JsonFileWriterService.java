package com.skowron.Rekrutacja.services.fileWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skowron.Rekrutacja.repositories.FileRepository;

import java.util.function.Function;

public abstract class JsonFileWriterService<T> implements FileWriterService<T> {

    private final ObjectMapper objectMapper;
    private final FileRepository fileRepository;
    private final String outputDir;
    private final Function<T, String> fileNameProvider;

    public JsonFileWriterService(ObjectMapper objectMapper, FileRepository fileRepository, String outputDir, Function<T, String> fileNameProvider) {
        this.objectMapper = objectMapper;
        this.fileRepository = fileRepository;
        this.outputDir = outputDir;
        this.fileNameProvider = fileNameProvider;
    }

    @Override
    public void saveObjectToFile(T object) {

        try {
            String json = objectMapper.writeValueAsString(object);
            String fileName = fileNameProvider.apply(object);

            fileRepository.save(outputDir, fileName, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing JSON", e);
        }
    }
}
