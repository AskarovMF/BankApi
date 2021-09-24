package ru.askarov.bankapi.model;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class TransferBalance {
    private long from;
    private long to;
    private BigDecimal amount;

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
