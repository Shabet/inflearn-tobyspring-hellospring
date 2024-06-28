package tobyspring.hellospring.v8_1;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExRateProvider {
    BigDecimal getExRate(String currency) throws IOException;
}
