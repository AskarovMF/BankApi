package ru.askarov.bankapi.model;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class Card {

    @Column(name = "CARD_ID", nullable = false)
    private long number;

    protected Card() {
    }

    public Card(Account account) {
        account.addCard(this);
    }

    public long getCardNumber() {
        return number;
    }

    public void setCardNumber(long cardNumber) {
        this.number = cardNumber;
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
