package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    //Lombok 의 @RequiredArgsConstructor 애너테이션의 기능을 이용하여 final 키워드가 붙은 필드의 생성자 코드를 자동으로 생성해준다.
    private final MemberRepository memberRepository;
    //DiscountPolicy discountPolicy = new FixDiscountPolicy(); 의존성 주입하기
    private final DiscountPolicy discountPolicy;

    @Autowired  //생성자 주입하기. 불변, 필수 의존관계에 사용. 생성자가 만약 딱 1개가 있다면 @Autowired 를 생략해도 주입이된다. !단 스프링 빈만 해당!
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;

    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
