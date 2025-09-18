package org.sampong.onlinebanking._common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {
    USD("0001", "USD", 1.0),
    KHR("0002", "KHR", 4000.0);

    private final String code;
    private final String currency;
    private final Double exchangeRate;
}
