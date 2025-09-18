package org.sampong.onlinebanking._common.base.res;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class ObjectResponse<T> {

    private T results;
    private Response response;

    public ObjectResponse() {
        this.response = Response.success();
    }

    public ObjectResponse(T results) {
        this.results = results;
        this.response = Response.success();
    }

    public ObjectResponse(T results, Response response) {
        this.results = results;
        this.response = response;
    }

}