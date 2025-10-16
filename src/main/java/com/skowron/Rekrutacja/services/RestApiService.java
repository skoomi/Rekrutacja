package com.skowron.Rekrutacja.services;

import java.util.List;

public interface RestApiService<T> {
    List<T> getAll();
}
