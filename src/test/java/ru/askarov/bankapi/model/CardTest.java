package ru.askarov.bankapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CardTest {
    private Card card;
    private Account account;

    @BeforeEach
    private void initCard() {
        account = new Account();
        account.setBalance(new BigDecimal(300));
        account.setAccount(1_000_000L);
        card = new Card(account);
        card.setNumber(333_333);
    }

    @Test
    void getCardNumber() {
        assertEquals(333_333, card.getNumber());
        assertNotEquals(555, card.getNumber());
    }

    @Test
    void getAccountNumber() {
        assertEquals(1_000_000L, card.getAccountNumber());
        assertNotEquals(234, card.getAccountNumber());
    }

    @Test
    void testEquals() {
        assertEquals(card, card);
        assertNotEquals(new Card(new Account()), card);
    }

    @Test
    void testHashCode() {
        assertNotEquals(card, new Card(new Account()));
        assertEquals(card, card);
    }
}