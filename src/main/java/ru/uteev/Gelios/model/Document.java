package ru.uteev.Gelios.model;

import java.math.BigDecimal;
import java.util.Date;


public class Document {
    private int agreementId;
    private int clientId;
    private int ProductId;
    private BigDecimal amount;
    private Date startDate;
    private Date timestamp;

    public Document(int clientId, int productId, BigDecimal amount, Date startDate) {
        this.clientId = clientId;
        ProductId = productId;
        this.amount = amount;
        this.startDate = startDate;
        timestamp = new Date();
    }

    public int getAgreementId() {
        return agreementId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getProductId() {
        return ProductId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    @Override
    public String toString() {
        return "Document{" +
                "agreementId=" + agreementId +
                ", clientId=" + clientId +
                ", ProductId=" + ProductId +
                ", amount=" + amount +
                ", startDate=" + startDate +
                ", timestamp=" + timestamp +
                '}';
    }
}
