package ru.uteev.Gelios.model;

import java.math.BigDecimal;

public class Statistics {
    private int count;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal sum;

    public Statistics(int count, BigDecimal minAmount, BigDecimal maxAmount, BigDecimal sum) {
        this.count = count;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.sum = sum;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "count=" + count +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", sum=" + sum +
                '}';
    }
}
