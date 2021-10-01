package ru.askarov.bankapi.service;

import ru.askarov.bankapi.model.Card;

public interface BankServiceCard {

    Card createCard(long numberAccount);
}
