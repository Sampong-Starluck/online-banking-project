package org.sampong.onlinebanking._common.base.rest;

import org.sampong.onlinebanking._common.base.res.BaseResponse;
import org.sampong.onlinebanking._common.base.res.PageResponse;

/**
 * T3: Page request
 * @param <T3>
 */
public interface BasePageRest<T1, T2, T3> extends BaseRest<T1, T3> {
    BaseResponse<PageResponse<T3>> getPageList(T2 t2);
}
