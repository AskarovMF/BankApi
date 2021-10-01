package ru.askarov.bankapi.service;

import ru.askarov.bankapi.model.Card;
import ru.askarov.bankapi.model.TransferBalance;

import java.math.BigDecimal;
import java.util.Set;

public interface BankServiceAccount {

    BigDecimal getBalance(long accountId);

    TransferBalance addBalance(TransferBalance transferBalance);

    Set<Card> getAllCards(long numberAaccount);
}
