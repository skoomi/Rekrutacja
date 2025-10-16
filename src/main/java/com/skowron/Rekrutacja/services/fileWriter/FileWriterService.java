package com.skowron.Rekrutacja.services.fileWriter;

public interface FileWriterService<T> {

    void saveObjectToFile(T object);
}
