package ru.askarov.bankapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.askarov.bankapi.model.Account;
import ru.askarov.bankapi.model.Card;
import ru.askarov.bankapi.repository.RepositoryAccount;
import ru.askarov.bankapi.repository.RepositoryCard;

@Service
public class BankServiceCardImpl implements BankServiceCard {

    private final RepositoryAccount repositoryAccount;
    private final RepositoryCard repositoryCard;

    @Autowired
    public BankServiceCardImpl(RepositoryAccount repositoryAccount, RepositoryCard repositoryCard) {
        this.repositoryAccount = repositoryAccount;
        this.repositoryCard = repositoryCard;
    }

    @Override
    public Card createCard(long numberAccount) {
        Account account = repositoryAccount.getAccount(numberAccount);
        Card card = new Card(account);
        repositoryCard.saveCard(card);
        return card;
    }
}
