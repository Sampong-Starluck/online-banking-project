package org.sampong.onlinebanking._common.base.res;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface Pagination<T> {

    default Pageable pagination(int page, int size) {
        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
    }

    default Pageable pagination(int page, int size, String field) {
        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, field));
    }

    default Pageable pagination(int page, int size, String field, Sort.Direction direction) {
        return PageRequest.of(page, size, Sort.by(direction, field));
    }

}
