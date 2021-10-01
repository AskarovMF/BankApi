package ru.askarov.bankapi.service;

import ru.askarov.bankapi.model.Card;

public interface BankServiceCard {

    public Card createCard(long numberAccount);
}
