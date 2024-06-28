package tobyspring.hellospring.v1;

import tobyspring.hellospring.Payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

abstract public class PaymentService {
    public Payment prepare(Long orderId, String currency, BigDecimal foreinCurrencyAmount) throws IOException {
        BigDecimal exRate = getExRate(currency);
        BigDecimal convertedAmount = foreinCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreinCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    abstract BigDecimal getExRate(String currency) throws IOException;
}
