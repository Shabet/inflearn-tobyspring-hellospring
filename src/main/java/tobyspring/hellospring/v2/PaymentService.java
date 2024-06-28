package tobyspring.hellospring.v2;

import tobyspring.hellospring.Payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {
//    private final WebApiExRateProvider exRateProvider;
    private final SimpleExRateProvider exRateProvider;

    public PaymentService() {
//        this.exRateProvider = new WebApiExRateProvider();
        this.exRateProvider = new SimpleExRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreinCurrencyAmount) throws IOException {
//        BigDecimal exRate = exRateProvider.getWebExRate(currency);
        BigDecimal exRate = exRateProvider.getExRate(currency);
        BigDecimal convertedAmount = foreinCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreinCurrencyAmount, exRate, convertedAmount, validUntil);
    }
}
