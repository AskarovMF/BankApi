package ru.askarov.bankapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CARDS")
public class Card {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Account_number", nullable = false)
    protected Account account;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_ID", nullable = false)
    private long number;

    @Version
    private long version;

    public Card() {
    }

    public Card(Account account) {
        this.account = account;
        account.addCard(this);
    }

    public long getAccountNumber() {
        return account.getAccount();
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number && Objects.equals(account, card.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, account);
    }
}
