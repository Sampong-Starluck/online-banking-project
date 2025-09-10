package org.sampong.onlinebanking._common.base;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
