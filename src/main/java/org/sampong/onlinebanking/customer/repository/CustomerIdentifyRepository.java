package org.sampong.onlinebanking.customer.repository;

import jakarta.transaction.Transactional;
import org.sampong.onlinebanking._common.base.BaseRepository;
import org.sampong.onlinebanking.customer.model.CustomerIdentification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerIdentifyRepository extends BaseRepository<CustomerIdentification> {

    @Transactional
    @Modifying
    @Query("update CustomerIdentification set status = false where id = :id and status = true")
    void delete(@Param("id") Long id);
}
