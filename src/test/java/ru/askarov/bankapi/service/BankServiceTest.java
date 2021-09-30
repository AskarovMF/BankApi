package ru.askarov.bankapi.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.askarov.bankapi.model.Account;
import ru.askarov.bankapi.model.Card;
import ru.askarov.bankapi.model.TransferBalance;
import ru.askarov.bankapi.repository.BalanceRepository;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {
    private Account account;
    private BalanceRepository repository;
    private BankService service;

    @BeforeEach
    private void initAccount() {
        repository = Mockito.mock(BalanceRepository.class);
        service = new BankService(repository);
        account = new Account();

        account.setAccount(1_000_000L);
        account.setBalance(new BigDecimal(500));
        account.addCard(new Card());

        Mockito.when(repository.getAccount(1_000_000L)).thenReturn(account);
    }

    @Test
    void getBalance() {
        BigDecimal res = service.getBalance(1_000_000L);

        assertEquals(new BigDecimal(500), res);
        Mockito.verify(repository).getAccount(1_000_000L);
    }

    @Test
    void addBalance() {
        TransferBalance exp = new TransferBalance(1_000_000L, new BigDecimal(600));
        TransferBalance transfer = new TransferBalance(1_000_000L, new BigDecimal(100));
        TransferBalance actual = service.addBalance(transfer);

        assertEquals(exp, actual);
        Mockito.verify(repository).getAccount(1_000_000L);
        Mockito.verify(repository).saveAccount(account);
    }

    @Test
    void createCard() {
        Card card = service.createCard(1_000_000L);

        assertEquals(1_000_000L, card.getAccountNumber());
        Mockito.verify(repository).getAccount(1_000_000L);
        Mockito.verify(repository).saveCard(card);
    }

    @Test
    void getAllCards() {
        Set<Card> cards = service.getAllCards(1_000_000L);

        assertEquals(1, cards.size());
        Mockito.verify(repository).getAccount(1_000_000L);
    }
}