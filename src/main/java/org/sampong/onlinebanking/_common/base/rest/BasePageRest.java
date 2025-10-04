package org.sampong.onlinebanking._common.base.rest;

import org.sampong.onlinebanking._common.base.res.PageResponse;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * T3: Page request
 * @param <T3>
 */
public interface BasePageRest<T1, T2, T3> extends BaseRest<T1, T3> {

    @GetMapping("/list")
    PageResponse<T3> getPageList(T2 t2);
}
