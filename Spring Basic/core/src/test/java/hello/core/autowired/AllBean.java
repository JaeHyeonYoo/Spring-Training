package hello.core.autowired;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBean {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService bean = ac.getBean(DiscountService.class);
        Member member = new Member(1L,"memberA",Grade.VIP);

        int fixDiscountPolicy = bean.discount(member, 1000, "fixDiscountPolicy");
        assertThat(fixDiscountPolicy).isEqualTo(1000);

        int rateDiscountPolicy = bean.discount(member, 30000, "rateDiscountPolicy");
        assertThat(rateDiscountPolicy).isEqualTo(3000);
    }

    static class DiscountService{

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        int discount(Member member, int price, String discountPolicy){
            DiscountPolicy discountPolicy1 = policyMap.get(discountPolicy);
            return discountPolicy1.discount(member, price);
        }
    }
}
