package com.shani.repository;

import java.util.List;

public interface LampRepository<K, V> {
    V save(V object);

    List<V> findAll();

    V findById(K key);

    V update(V object);

    K delete(V object);

    V updateLight(V object);
}
