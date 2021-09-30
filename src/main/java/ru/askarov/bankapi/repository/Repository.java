package ru.askarov.bankapi.repository;

import ru.askarov.bankapi.model.Account;
import ru.askarov.bankapi.model.Card;

public interface Repository {
    Account getAccount(long accountId);

    void saveAccount(Account account);

    void saveCard(Card card);
}
