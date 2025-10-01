package org.sampong.onlinebanking.transfer.controller.dto.request;

import lombok.Setter;
import org.sampong.onlinebanking._common.enumerate.Currency;
import org.sampong.onlinebanking._common.enumerate.TranceType;

public record TransferRequest(
        Long srcAccountId,
        Long targetAccountId,
        Double balance,
        Currency currency,
        TranceType type
) {
}
