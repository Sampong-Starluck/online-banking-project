package org.sampong.onlinebanking._common.base.rest;

import org.sampong.onlinebanking._common.base.res.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * T1: ObjectRequest, T2: Response
 * @param <T1>
 * @param <T2>
 */
public interface BaseRest <T1, T2> {

    @GetMapping("/{id}")
    BaseResponse<T2> get(@PathVariable Long id);

    @PostMapping
    BaseResponse<T2> save(@RequestBody T1 t1);

    @PutMapping
    BaseResponse<T2> update(@RequestBody T1 t1);

    @DeleteMapping
    BaseResponse<Void> delete(@PathVariable Long id);

    @GetMapping
    BaseResponse<List<T2>> findAll();

}
