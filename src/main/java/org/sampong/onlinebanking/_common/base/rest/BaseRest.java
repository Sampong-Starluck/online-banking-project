package org.sampong.onlinebanking._common.base.rest;

import org.sampong.onlinebanking._common.base.res.BaseResponse;
import org.sampong.onlinebanking._common.base.res.MessageResponse;
import org.sampong.onlinebanking._common.base.res.ObjectResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * T1: ObjectRequest, T2: Response
 * @param <T1>
 * @param <T2>
 */
public interface BaseRest <T1, T2> {

    @GetMapping("/{id}")
    ObjectResponse<T2> get(@PathVariable Long id);

    @PostMapping
    ObjectResponse<T2> save(@RequestBody T1 t1);

    @PutMapping
    ObjectResponse<T2> update(@RequestBody T1 t1);

    @DeleteMapping
    MessageResponse delete(@PathVariable Long id);

    @GetMapping
    ObjectResponse<List<T2>> findAll();

}
