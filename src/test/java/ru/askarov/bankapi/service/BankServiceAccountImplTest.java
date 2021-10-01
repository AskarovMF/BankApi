package ru.askarov.bankapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.askarov.bankapi.model.Account;
import ru.askarov.bankapi.model.Card;
import ru.askarov.bankapi.model.TransferBalance;
import ru.askarov.bankapi.repository.RepositoryAccount;
import ru.askarov.bankapi.repository.RepositoryCard;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankServiceAccountImplTest {
    private Account account;
    private RepositoryAccount repositoryAccount;
    private RepositoryCard repositoryCard;
    private BankServiceAccount serviceAccount;
    private BankServiceCard serviceCard;

    @BeforeEach
    private void initAccount() {
        repositoryAccount = Mockito.mock(RepositoryAccount.class);
        repositoryCard = Mockito.mock(RepositoryCard.class);
        serviceAccount = new BankServiceAccountImpl(repositoryAccount);
        serviceCard = new BankServiceCardImpl(repositoryAccount, repositoryCard);
        account = new Account();

        account.setAccount(1_000_000L);
        account.setBalance(new BigDecimal(500));
        account.addCard(new Card());

        Mockito.when(repositoryAccount.getAccount(1_000_000L)).thenReturn(account);
    }

    @Test
    void getBalance() {
        BigDecimal res = serviceAccount.getBalance(1_000_000L);

        assertEquals(new BigDecimal(500), res);
        Mockito.verify(repositoryAccount).getAccount(1_000_000L);
    }

    @Test
    void addBalance() {
        TransferBalance exp = new TransferBalance(1_000_000L, new BigDecimal(600));
        TransferBalance transfer = new TransferBalance(1_000_000L, new BigDecimal(100));
        TransferBalance actual = serviceAccount.addBalance(transfer);

        assertEquals(exp, actual);
        Mockito.verify(repositoryAccount).getAccount(1_000_000L);
        Mockito.verify(repositoryAccount).saveAccount(account);
    }

    @Test
    void createCard() {
        Card card = serviceCard.createCard(1_000_000L);

        assertEquals(1_000_000L, card.getAccountNumber());
        Mockito.verify(repositoryAccount).getAccount(1_000_000L);
        Mockito.verify(repositoryCard).saveCard(card);
    }

    @Test
    void getAllCards() {
        Set<Card> cards = serviceAccount.getAllCards(1_000_000L);

        assertEquals(1, cards.size());
        Mockito.verify(repositoryAccount).getAccount(1_000_000L);
    }
}