package org.sampong.onlinebanking._common.configuration;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class AccountLockManager {
    private final ConcurrentHashMap<Long, ReentrantLock> locks = new ConcurrentHashMap<>();

    public ReentrantLock getLock(Long accountId) {
        return locks.computeIfAbsent(accountId, id -> new ReentrantLock(true));
    }
}
