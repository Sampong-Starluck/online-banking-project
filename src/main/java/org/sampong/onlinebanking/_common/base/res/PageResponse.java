package org.sampong.onlinebanking._common.base.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    @Schema(description = "page number", example = "1")
    private Integer page;

    @Schema(description = "records per page", example = "10")
    private Integer size;

    @Schema(description = "total records found", example = "100")
    private Long total;

    @Schema(description = "total page number", example = "10")
    private Long totalPage;

    private List<T> results;

    private Response response = Response.success();
}
