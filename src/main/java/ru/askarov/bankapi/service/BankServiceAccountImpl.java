package ru.askarov.bankapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.askarov.bankapi.model.Account;
import ru.askarov.bankapi.model.Card;
import ru.askarov.bankapi.model.TransferBalance;
import ru.askarov.bankapi.repository.RepositoryAccount;
import ru.askarov.bankapi.repository.RepositoryAccountImpl;
import ru.askarov.bankapi.repository.RepositoryCard;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class BankServiceAccountImpl implements BankServiceAccount{
    private final RepositoryAccount repositoryAccount;

    @Autowired
    public BankServiceAccountImpl(RepositoryAccount repositoryAccount) {
        this.repositoryAccount = repositoryAccount;
    }

    public BigDecimal getBalance(long accountId) {
        Account account = repositoryAccount.getAccount(accountId);
        return account.getBalance();
    }

    public TransferBalance addBalance(TransferBalance transferBalance) {
        if (transferBalance.getAmount() == null
                || (transferBalance.getAmount().compareTo(new BigDecimal(0))) < 0)
            throw new IllegalArgumentException("Неверная сумма");

        Account account = repositoryAccount.getAccount(transferBalance.getTo());
        BigDecimal amount = account.getBalance().add(transferBalance.getAmount());
        account.setBalance(amount);
        repositoryAccount.saveAccount(account);
        return new TransferBalance(account.getAccount(), amount);
    }

    public Set<Card> getAllCards(long numberAaccount) {
        Account account = repositoryAccount.getAccount(numberAaccount);
        return account.getCards();
    }
}
