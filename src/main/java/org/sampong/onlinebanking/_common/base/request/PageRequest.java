package org.sampong.onlinebanking._common.base.request;


import lombok.Data;

@Data
public class PageRequest {
    String query;
    Integer page;
    Integer size;
}
