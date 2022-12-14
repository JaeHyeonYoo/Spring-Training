package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class orderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test

    void order(){

        //when
        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

        //given
         Order order = orderService.createOrder(1L,"아메리카노",10000);

        //then
        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);

    }
}
