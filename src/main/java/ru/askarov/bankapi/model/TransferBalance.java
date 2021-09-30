package ru.askarov.bankapi.model;

import java.math.BigDecimal;
import java.util.Objects;

public class TransferBalance {
    private long to;
    private BigDecimal amount;

    public TransferBalance() {
    }

    public TransferBalance(long to, BigDecimal amount) {
        this.to = to;
        this.amount = amount;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getTo() {
        return to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferBalance balance = (TransferBalance) o;
        return to == balance.to && Objects.equals(amount, balance.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, amount);
    }
}
