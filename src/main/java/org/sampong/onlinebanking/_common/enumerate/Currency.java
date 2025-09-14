package org.sampong.onlinebanking._common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {
    USD("0001", "USD"),
    KHR("0002", "KHR");

    private final String code;
    private final String currency;
}
