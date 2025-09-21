package org.sampong.onlinebanking.transfer.controller.dto.request;

import org.sampong.onlinebanking._common.enumerate.Currency;

public record WithdrawRequest(
        Long accountId,
        Currency currency,
        Double balance
) {
}
