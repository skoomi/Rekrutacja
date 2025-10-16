package com.skowron.Rekrutacja.repositories;

public interface FileRepository {
    void save(String directory, String fileName, String content);
}
