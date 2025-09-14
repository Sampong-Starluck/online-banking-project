package org.sampong.onlinebanking._common.base.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    private Integer page;

    private Integer size;

    private Long total;

    private Long totalPage;

    private List<T> results;
    private Response response = Response.success();

    public PageResponse(Integer page, Integer size, Long total, Long totalPage, List<T> results) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.totalPage = totalPage;
        this.results = results;
        this.response = Response.success();
    }
}