package org.sampong.onlinebanking._common.base.service;

import org.sampong.onlinebanking._common.base.request.PageRequest;
import org.sampong.onlinebanking._common.base.res.Pagination;
import org.springframework.data.domain.Page;

public interface BasePageService<T, T1 extends PageRequest> extends BaseService<T>, Pagination {
    Page<T> findAllPage(T1 t1);

}
