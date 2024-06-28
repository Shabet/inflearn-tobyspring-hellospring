package tobyspring.hellospring.v5;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExRateProvider {
    BigDecimal getExRate(String currency) throws IOException;
}
