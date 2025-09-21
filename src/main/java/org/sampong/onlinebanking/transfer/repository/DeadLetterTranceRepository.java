package org.sampong.onlinebanking.transfer.repository;

import org.sampong.onlinebanking._common.base.BaseRepository;
import org.sampong.onlinebanking._common.enumerate.TranceStatus;
import org.sampong.onlinebanking.transfer.model.DeadLetterTransaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeadLetterTranceRepository extends BaseRepository<DeadLetterTransaction> {
    List<DeadLetterTransaction> findAllByTrxnStatusAndStatusTrue(TranceStatus status);
}
