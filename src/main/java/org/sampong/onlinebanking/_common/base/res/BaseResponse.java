package org.sampong.onlinebanking._common.base.res;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BaseResponse<T> {

    private Response response;
    private T result;
    private Integer code;
    private String msg;

    // ✅ Success factory method
    public static <T> BaseResponse<T> success(T result) {
        return BaseResponse.<T>builder()
                .response(Response.success())
                .result(result)
                .build();
    }

    // ✅ Error factory method
    public static <T> BaseResponse<T> error(String msg) {
        return BaseResponse.<T>builder()
                .response(Response.error())
                .msg(msg)
                .build();
    }

    // ✅ Custom code + message factory
    public static <T> BaseResponse<T> withCode(int code, String msg, T result) {
        return BaseResponse.<T>builder()
                .response(Response.success())
                .result(result)
                .code(code)
                .msg(msg)
                .build();
    }

    // ✅ Custom code + message (no result)
    public static <T> BaseResponse<T> withCode(int code, String msg) {
        return BaseResponse.<T>builder()
                .response(Response.success())
                .code(code)
                .msg(msg)
                .build();
    }

    // ✅ Paginated Response (wraps PageResponse<T>)
    public static <T> BaseResponse<PageResponse<T>> paginated(org.springframework.data.domain.Page<T> pageData) {
        PageResponse<T> pageResponse = PageResponse.<T>builder()
                .page(pageData.getNumber() + 1) // Convert 0-indexed Spring pages → 1-indexed
                .size(pageData.getSize())
                .total(pageData.getTotalElements())
                .totalPage((long) pageData.getTotalPages())
                .results(pageData.getContent())
                .response(Response.success())
                .build();

        return BaseResponse.<PageResponse<T>>builder()
                .response(Response.success())
                .result(pageResponse)
                .build();
    }
}

/*
@Component
public record BaseResponse() {
    public Map<String, Object> response(Object obj) {
        var res = new HashMap<String, Object>();
        res.put("response", Response.success());
        res.put("result", obj);
        if (obj == null) {
            res.put("response", Response.error());
            return res;
        }
        return res;
    }

    public Map<String, Object> responseCode(int code, String msg) {
        var res = new HashMap<String, Object>();
        res.put("response", Response.success());
        res.put("code", code);
        res.put("msg", msg);
        return res;
    }
}
*/
