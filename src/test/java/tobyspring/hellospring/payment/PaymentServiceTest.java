package tobyspring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.exrate.WebApiExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void prepare() {
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider(new ApiTemplate()), this.clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        //환율정보 가져온다
        assertThat(payment.getExRate()).isNotNull();

        //원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualTo(payment.getExRate().multiply(payment.getForeinCurrencyAmount()));

        //원화환산금액 유효시간 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }


    @Test
    void convertedAmount() {
        testAmount(valueOf(500), valueOf(5_000), this.clock);
        testAmount(valueOf(1_000), valueOf(10_000), this.clock);
        testAmount(valueOf(3_000), valueOf(30_000), this.clock);

        //원화환산금액 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    @Test
//    @DisplayName("원화환산금액 유효시간 계산")
    void validUntil() {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), this.clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        //valid until이 prepare() 30분 뒤로 설정되었는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        //환율정보 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        //원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}