package tobyspring.hellospring.v5;

public class ObjectFactory {
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}
