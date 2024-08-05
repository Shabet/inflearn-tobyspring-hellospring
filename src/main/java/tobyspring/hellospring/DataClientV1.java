package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.data.OrderRepository;
import tobyspring.hellospring.order.Order;

import java.math.BigDecimal;

public class DataClientV1 {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);

        // transactio begin
        Order order = new Order("100", BigDecimal.TEN);
        repository.save(order);

        System.out.println(order);

        try {
            Order order2 = new Order("100", BigDecimal.ONE);
            repository.save(order2);
        } catch (RuntimeException e) {
//            System.out.println("주문번호 충돌을 복구하는 작업");
            e.printStackTrace();
            throw e;
        }
        // commit
    }
}
