package org.sampong.onlinebanking._common.base.res;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class MessageResponse {

    private Response response;

    public MessageResponse() {
        this.response = Response.success();
    }

    public MessageResponse(Response response) {
        this.response = response;
    }

}