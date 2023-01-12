package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        //생성자로 주입을 해주면 순수자바 코드로 테스트를 진행해도 무리가 없다.
        //final 키워드도 사용가능하여 불변의 상태로 만들어줄 수 있다.
        //fival 키워드 사용으로 인해 생성자에서만 값을 줄 수 있고 누락도 방지할 수 있다.
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"name", Grade.VIP));

        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order orderA = orderServiceImpl.createOrder(1L, "orderA", 1000);
        assertThat(orderA.getItemName()).isEqualTo("orderA");
    }

}