package tobyspring.hellospring.v3;

import tobyspring.hellospring.Payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {
    private final ExRateProvider exRateProvider;

    public PaymentService() {
//        this.exRateProvider = new SimpleExRateProvider();
        this.exRateProvider = new WebApiExRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreinCurrencyAmount) throws IOException {
        BigDecimal exRate = exRateProvider.getExRate(currency);
        BigDecimal convertedAmount = foreinCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreinCurrencyAmount, exRate, convertedAmount, validUntil);
    }
}
