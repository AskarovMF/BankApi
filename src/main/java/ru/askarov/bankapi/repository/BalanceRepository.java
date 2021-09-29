package ru.askarov.bankapi.repository;

import org.springframework.stereotype.Repository;
import ru.askarov.bankapi.model.Account;
import ru.askarov.bankapi.model.Card;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class BalanceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Account getAccount(long accountId) {
        Account account = entityManager.find(Account.class, accountId);
        if (account == null) throw new IllegalArgumentException("Номер счета не найден");
        return account;
    }

    public void saveAccount(Account account) {
        entityManager.persist(account);
    }

    public void saveCard(Card card) {
        entityManager.persist(card);
    }
}
