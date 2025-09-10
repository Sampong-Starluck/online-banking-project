package org.sampong.onlinebanking._common.base;

import java.util.Optional;

public interface BaseService<T> {
    Optional<T> findById(Long id);
    Optional<T> findByName(String name);
    T addNew(T t);
    T updateObj(T t);
    void delete(Long id);
}
