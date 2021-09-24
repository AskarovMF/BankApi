package ru.askarov.bankapi.model;

import java.math.BigDecimal;

public class TransferBalance {
    private long from;
    private long to;
    private BigDecimal amount;

    public TransferBalance() {
    }

    public TransferBalance(long from, long to, BigDecimal amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public TransferBalance(long to, BigDecimal amount) {
        this.to = to;
        this.amount = amount;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
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
