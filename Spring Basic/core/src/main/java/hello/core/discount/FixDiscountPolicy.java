package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
// 만약 DiscountPolicy 타입에 등록된 빈이 rate 와 fix 2개 이상이라면 @Autowired 필드 명 매칭과 @Qualifier, @Primary 를 사용할 수 있다.
// 먼저 @Autowired 방식은 주입하는 타입의 필드 명을 rate 나 fix 처럼 빈에 등록된 이름으로 지정해준다.
// 생성자 파라미터 명을 직접 지정해줘도 된다. 참고 @Autowired 필드 명 매칭은 먼저 타입 매칭이 진행되고 등록된 빈이 2개 이상일 때 필드 명 매칭이 일어난다.
// @Qualifier 를 사용할 때는 각각 등록하는 빈에 @Qualifier("first")처럼 추가를 해주고 주입하려는 타입 앞에 @Qualifier("first") 처럼 추가해주어
// 서로 이어주는 역할을 해준다고 보면된다.
// 마지막으로 @Primary 는 우선순위를 주고싶은 빈에 추가를 해주면된다. 메인 DB와 서브 DB가 있을 때 메인 DB에 @Primary 를 붙여주면 우선권을 가진다.
// @Primary 와 @Qualifier 는 메인 DB와 서브 DB를 예로 들 수 있다. 메인 DB는 우선권을 가지는게 맞기 때문에 @Primary 를 붙여준다.
// 그리고 서브 DB는 가끔 사용하기 때문에 @Qualifier 로 지정을 해주어 필요할 때 추가하여 사용할 수 있다.
// 만약 @Primary 와 @Qualifier 애너테이션이 같이 지정된다면 우선권은 @Qualifier 에게 있다.

@Component
@MainDiscountPolicy //@Qualifier 를 재정의한 Annotation 을 사용 == @Qualifier("mainDiscountPolicy") 오타 방지 효기
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; // 1000원 할인w

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else return 0;
    }
}
