package ru.askarov.bankapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.askarov.bankapi.model.TransferBalance;
import ru.askarov.bankapi.repository.BalanceRepository;

import java.math.BigDecimal;

@Service
public class BankService {
    private final BalanceRepository repository;

    @Autowired
    public BankService(BalanceRepository balanceRepository) {
        this.repository = balanceRepository;
    }

    public BigDecimal getBalance(long accountId) {
        BigDecimal balance = repository.getBalanceForId(accountId);
        if (balance == null) {
            throw new IllegalArgumentException();
        }
        return balance;
    }

    public void makeTransfer(TransferBalance transferBalance) {
        BigDecimal fromBalance = repository.getBalanceForId(transferBalance.getFrom());
        BigDecimal toBalance = repository.getBalanceForId(transferBalance.getTo());
        if (fromBalance == null || toBalance == null) throw new IllegalArgumentException("No ids");
        if (transferBalance.getAmount().compareTo(fromBalance) > 0) throw new IllegalArgumentException("No money");

        BigDecimal updatedFromBalance = fromBalance.subtract(transferBalance.getAmount());
        BigDecimal updatedToBalance = toBalance.add(transferBalance.getAmount());
        repository.save(transferBalance.getFrom(), updatedFromBalance);
        repository.save(transferBalance.getTo(), updatedToBalance);
    }

    public BigDecimal addMoney(Long to, BigDecimal amount) {
        BigDecimal currentBalance = repository.getBalanceForId(to);
        if (currentBalance == null) {
            repository.save(to, amount);
            return amount;
        } else {
            BigDecimal updatedBalance = repository.getBalanceForId(to).add(amount);
            repository.save(to, updatedBalance);
            return updatedBalance;
        }
    }
}
