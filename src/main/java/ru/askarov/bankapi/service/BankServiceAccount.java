package ru.askarov.bankapi.service;

import ru.askarov.bankapi.model.Card;
import ru.askarov.bankapi.model.TransferBalance;

import java.math.BigDecimal;
import java.util.Set;

public interface BankServiceAccount {

    BigDecimal getBalance(long accountId);

    public TransferBalance addBalance(TransferBalance transferBalance);

    public Set<Card> getAllCards(long numberAaccount);
}
