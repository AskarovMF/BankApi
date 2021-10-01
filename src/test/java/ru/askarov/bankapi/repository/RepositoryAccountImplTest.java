package ru.askarov.bankapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.askarov.bankapi.model.Account;
import ru.askarov.bankapi.model.Card;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryAccountImplTest {
    private Account account;
    private RepositoryAccountImpl repository;
    private Card card;
    private EntityManager entityManager;

    @BeforeEach
    private void initAccount() {
        entityManager = Mockito.mock(EntityManager.class);
        repository = new RepositoryAccountImpl(entityManager);
        account = new Account();
        card = new Card(account);

        card.setNumber(3_000);

        account.setAccount(1_000_000L);
        account.setBalance(new BigDecimal(500));
    }

    @Test
    void getAccount() {
        Mockito.when(entityManager.find(Account.class, 1_000_000L)).thenReturn(account);
        Account exp = repository.getAccount(1_000_000L);

        assertEquals(account, exp);
        Mockito.verify(entityManager).find(Account.class, 1_000_000L);
    }

    @Test
    void saveAccount() {
        repository.saveAccount(account);
        Mockito.verify(entityManager).persist(account);
    }

    @Test
    void saveCard() {
        repository.saveCard(card);
        Mockito.verify(entityManager).persist(card);
    }
}