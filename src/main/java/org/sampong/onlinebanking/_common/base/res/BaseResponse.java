package org.sampong.onlinebanking._common.base.res;

import org.sampong.onlinebanking._common.base.request.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface BaseResponse {

    // ✅ Object response (single or list)
    static <T> ObjectResponse<T> success(T obj) {
        return new ObjectResponse<>(obj, Response.success());
    }

    // ✅ Error response
    static <T> ObjectResponse<T> error() {
        return new ObjectResponse<>(null, Response.error());
    }

    // ✅ Error response
    static <T> ObjectResponse<T> error(String message) {
        return new ObjectResponse<>(null, new Response(400, message));
    }

    // ✅ Message only
    static MessageResponse withCode(Integer code, String msg) {
        return new MessageResponse(new Response(code, msg));
    }

    // ✅ Paginated response
    static <T> PageResponse<T> paginated(Page<T> obj) {
        int size = obj.getPageable().getPageSize();
        int page = obj.getPageable().getPageNumber();
        long total = obj.getTotalElements();
        long totalPage = (total + size) / size;

        return new PageResponse<>(page, size, total, totalPage, obj.getContent());
    }
}


/*public interface BaseResponse<T> {

    default ObjectResponse<T> res(T obj) {
        return new ObjectResponse<>(obj);
    }

    default MessageResponse res() {
        return new MessageResponse();
    }

    default PageResponse<T> res(Page<T> obj) {
        int size = obj.getPageable().getPageSize();
        int page = obj.getPageable().getPageNumber();
        long total = obj.getTotalElements();
        long totalPage = (total + size) / size;

        return new PageResponse<>(page, size, total, totalPage, obj.getContent());
    }
}*/

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
