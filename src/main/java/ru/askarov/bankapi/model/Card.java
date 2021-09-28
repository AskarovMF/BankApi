package ru.askarov.bankapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CARDS")
public class Card {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_ID", nullable = false)
    private long number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Account_number", nullable = false)
    Account account;

    @Version
    private long version;

    protected Card() {
    }

    public Card(Account account) {
        this.account = account;
        account.addCard(this);
    }

    public long getCardNumber() {
        return number;
    }

    public long getAccountNumber(){
        return account.getAccount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
