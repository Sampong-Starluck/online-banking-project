package org.sampong.onlinebanking._common.base.res;

import org.sampong.onlinebanking._common.base.request.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface BaseResponse {

    // Object response (single or list)
    static <T> ObjectResponse<T> success(T obj) {
        return new ObjectResponse<>(obj, Response.success());
    }

    // Error response
    static <T> ObjectResponse<T> error() {
        return new ObjectResponse<>(null, Response.error());
    }

    // Error response
    static <T> ObjectResponse<T> error(String message) {
        return new ObjectResponse<>(null, new Response(400, message));
    }

    // Message only
    static MessageResponse withCode(Integer code, String msg) {
        return new MessageResponse(new Response(code, msg));
    }

    // Paginated response
    static <T> PageResponse<T> paginated(Page<T> obj) {
        int size = obj.getPageable().getPageSize();
        int page = obj.getPageable().getPageNumber();
        long total = obj.getTotalElements();
        long totalPage = (total + size) / size;

        return new PageResponse<>(page, size, total, totalPage, obj.getContent());
    }
}
