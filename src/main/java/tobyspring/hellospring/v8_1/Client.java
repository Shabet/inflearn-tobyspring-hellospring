package tobyspring.hellospring.v8_1;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.Payment;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);
        OrderService orderService = beanFactory.getBean(OrderService.class);

        ObjectFactory objectFactory = beanFactory.getBean(ObjectFactory.class);
        PaymentService paymentService1 = objectFactory.paymentService();
        PaymentService paymentService2 = objectFactory.paymentService();

        System.out.println(paymentService1 == paymentService2);
        System.out.println(paymentService.exRateProvider == orderService.exRateProvider);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
