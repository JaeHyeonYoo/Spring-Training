package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("등급이 vip가 적용되었을 때")
    void vip_o(){

        //give
        Member member = new Member(1L,"memberA", Grade.VIP);

        //when
        int discountPrice = discountPolicy.discount(member,10000);

        //then
        Assertions.assertThat(discountPrice).isEqualTo(1000);
    }
    @Test
    @DisplayName("등급이 VIP가 아닌 경우")
    void vip_x(){

        //give
        Member member = new Member(1L,"memberB",Grade.BASIC);

        //when
        int discountPrice = discountPolicy.discount(member,10000);

        //then
        Assertions.assertThat(discountPrice).isEqualTo(0);
    }
}
