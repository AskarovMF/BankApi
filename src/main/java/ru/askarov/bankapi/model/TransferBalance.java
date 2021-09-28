package ru.askarov.bankapi.model;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

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
}
