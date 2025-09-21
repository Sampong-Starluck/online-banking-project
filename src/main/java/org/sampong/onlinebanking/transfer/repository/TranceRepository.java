package org.sampong.onlinebanking.transfer.repository;

import jakarta.transaction.Transactional;
import org.sampong.onlinebanking._common.base.BaseRepository;
import org.sampong.onlinebanking.transfer.model.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TranceRepository extends BaseRepository<Transaction> {

    @Transactional
    @Modifying
    @Query(value = "update Transaction set status = false where id = :id and status = true")
    void delete(@Param("id") Long id);
}