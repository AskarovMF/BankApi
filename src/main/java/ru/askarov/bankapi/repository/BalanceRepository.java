package ru.askarov.bankapi.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BalanceRepository {
    private final Map<Long, BigDecimal> storage = new HashMap<>();

    public BigDecimal getBalanceForId(long accountId) {
        return storage.get(accountId);
    }

    public void save(long id, BigDecimal amount){
        storage.put(id, amount);
    }
}
