package ru.askarov.bankapi.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "BANK_ACCOUNTS")
public class Account {

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @CollectionTable(name = "CARDS")
    protected Set<Card> cards = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMBER")
    private long account;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Version
    private long version;

    public Account() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void addCard (Card card){
        cards.add(card);
    }

    public long getAccount() {
        return account;
    }

    public Set<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account1 = (Account) o;
        return account == account1.account && Objects.equals(balance, account1.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, balance);
    }
}
