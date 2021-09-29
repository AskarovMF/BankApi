package ru.askarov.bankapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.askarov.bankapi.model.Account;
import ru.askarov.bankapi.model.Card;
import ru.askarov.bankapi.model.TransferBalance;
import ru.askarov.bankapi.repository.BalanceRepository;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class BankService {
    private final BalanceRepository repository;

    @Autowired
    public BankService(BalanceRepository balanceRepository) {
        this.repository = balanceRepository;
    }

    public BigDecimal getBalance(long accountId) {
        Account account = repository.getAccount(accountId);
        return account.getBalance();
    }

    public TransferBalance addBalance(TransferBalance transferBalance) {
        if (transferBalance.getAmount() == null ||
                (transferBalance.getAmount().compareTo(new BigDecimal(0))) < 0)
            throw new IllegalArgumentException("Неверная сумма");

        Account account = repository.getAccount(transferBalance.getTo());
        BigDecimal amount = account.getBalance().add(transferBalance.getAmount());
        account.setBalance(amount);
        repository.saveAccount(account);
        return new TransferBalance(account.getAccount(), amount);
    }

    public Card createCard(long numberAccount) {
        Account account = repository.getAccount(numberAccount);
        Card card = new Card(account);
        repository.saveCard(card);
        return card;
    }

    public Set<Card> getAllCards(long numberAaccount) {
        Account account = repository.getAccount(numberAaccount);
        return account.getCards();
    }
}
