package ru.askarov.bankapi.repository;

import ru.askarov.bankapi.model.Account;

public interface RepositoryAccount {
    Account getAccount(long accountId);

    void saveAccount(Account account);
}
