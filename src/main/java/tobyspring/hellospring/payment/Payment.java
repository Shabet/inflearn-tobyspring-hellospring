package tobyspring.hellospring.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class Payment {
    private Long orderId;
    private String currency;
    private BigDecimal foreinCurrencyAmount;
    private BigDecimal exRate;
    private BigDecimal convertedAmount;
    private LocalDateTime validUntil;

    public Payment(Long orderId, String currency, BigDecimal foreinCurrencyAmount, BigDecimal exRate, BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreinCurrencyAmount = foreinCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    public static Payment createPrepared(Long orderId, String currency, BigDecimal foreinCurrencyAmount, BigDecimal exRate, LocalDateTime now) {
        BigDecimal convertedAmount = foreinCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = now.plusMinutes(30);

        return new Payment(orderId, currency, foreinCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    public boolean isValid(Clock clock) {
        return LocalDateTime.now(clock).isBefore(this.validUntil);
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getForeinCurrencyAmount() {
        return foreinCurrencyAmount;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "orderId=" + orderId +
                ", currency='" + currency + '\'' +
                ", foreinCurrencyAmount=" + foreinCurrencyAmount +
                ", exRate=" + exRate +
                ", convertedAmount=" + convertedAmount +
                ", validUntil=" + validUntil +
                '}';
    }
}
