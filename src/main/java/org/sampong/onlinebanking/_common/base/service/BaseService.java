package org.sampong.onlinebanking._common.base.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    Optional<T> findById(Long id);
    Optional<List<T>> findAllList();
    T addNew(T t);
    T updateObj(T t);
    void delete(Long id);
}
