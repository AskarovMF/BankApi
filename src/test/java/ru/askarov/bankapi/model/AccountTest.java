package ru.askarov.bankapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account;

    @BeforeEach
    private void initAccount() {
        account = new Account();
        account.setBalance(new BigDecimal(100));
        account.setAccount(1_000_000L);
    }

    @Test
    void getBalance() {
        assertEquals(new BigDecimal(100), account.getBalance());
    }

    @Test
    void setBalance() {
        account.setBalance(new BigDecimal(500));
        assertNotEquals(100, account.getBalance());
        assertEquals(new BigDecimal(500), account.getBalance());
    }

    @Test
    void addCard() {
        Card card = new Card();
        card.setNumber(5_000);
        account.addCard(card);

        assertEquals(1, account.getCards().size());
        assertTrue(account.getCards().contains(card));
        assertFalse(account.getCards().contains(new Card()));
    }

    @Test
    void getAccount() {
        assertEquals(1_000_000, account.getAccount());
        assertNotEquals(3_000, account.getAccount());
    }

    @Test
    void getCards() {
        Card card1 = new Card();
        Card card2 = new Card();
        Card card3 = new Card();

        Set<Card> set = new HashSet<>();
        set.add(card1);
        set.add(card2);
        set.add(card3);

        account.addCard(card1);
        account.addCard(card2);
        account.addCard(card3);

        assertIterableEquals(set, account.getCards());
        assertNotEquals(account.getCards(), new HashSet<>());
    }

    @Test
    void testEquals() {
        assertEquals(account, account);
        assertNotEquals(account, new Account());
    }

    @Test
    void testHashCode() {
        assertEquals(account.hashCode(), account.hashCode());
        assertNotEquals(account.hashCode(), new Account().hashCode());
    }
}