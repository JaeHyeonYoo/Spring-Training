package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService stateful1 = ac.getBean(StatefulService.class);
        StatefulService stateful2 = ac.getBean(StatefulService.class);

        //맴버 A가 10000원 주문
        int memberAprice = stateful1.order("memberA", 10000);
        //맴버 B가 20000원 주문
        int memberBprice = stateful2.order("memberB", 20000);

        System.out.println("memberAprice = " + memberAprice);
        System.out.println("memberBprice = " + memberBprice);

        Assertions.assertThat(memberAprice).isEqualTo(10000);
    }
    static class TestConfig{
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}