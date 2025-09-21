package org.sampong.onlinebanking._common.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sampong.onlinebanking._common.enumerate.TranceStatus;
import org.sampong.onlinebanking._common.enumerate.TranceType;
import org.sampong.onlinebanking.transfer.controller.dto.request.TransferRequest;
import org.sampong.onlinebanking.transfer.model.DeadLetterTransaction;
import org.sampong.onlinebanking.transfer.repository.DeadLetterTranceRepository;
import org.sampong.onlinebanking.transfer.service.TranceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeadLetterProcessor {

    private final DeadLetterTranceRepository dlqRepository;
    private final TranceService service;

    @Scheduled(fixedDelay = 60000) // every 60 seconds
    @Transactional
    public void retryDeadLetters() {
        List<DeadLetterTransaction> dlqs = dlqRepository.findAllByTrxnStatusAndStatusTrue(TranceStatus.PENDING);

        for (DeadLetterTransaction dlq : dlqs) {
            try {
                log.info("🔄 Retrying DLQ txn ID {}", dlq.getId());
                var request = new TransferRequest(
                        dlq.getFromAccountId(),
                        dlq.getFromAccountId(),
                        dlq.getAmount(),
                        dlq.getCurrency(),
                        TranceType.valueOf(dlq.getType())
                );

                String result = service.processTransaction(request);

                if ("SUCCESS".equals(result)) {
                    dlq.setTrxnStatus(TranceStatus.SUCCESS);
                } else {
                    dlq.setRetryCount(dlq.getRetryCount() + 1);
                    if (dlq.getRetryCount() >= 5) {
                        dlq.setTrxnStatus(TranceStatus.FAILED_FINAL);
                    }
                }
                dlqRepository.save(dlq);

            } catch (Exception e) {
                dlq.setRetryCount(dlq.getRetryCount() + 1);
                dlq.setReason("Exception: " + e.getMessage());
                if (dlq.getRetryCount() >= 5) {
                    dlq.setTrxnStatus(TranceStatus.FAILED_FINAL);
                }
                dlqRepository.save(dlq);
            }
        }
    }
}
