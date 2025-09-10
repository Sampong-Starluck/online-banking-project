package org.sampong.onlinebanking._common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Integer status;
    private String message;

    public static Response success() {
        return new Response(200, "Success");
    }

    public static Response error() {
        return new Response(404, "Error");
    }
}