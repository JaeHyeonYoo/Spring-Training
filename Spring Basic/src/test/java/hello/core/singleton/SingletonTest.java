package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        // 1.호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 2.호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        // 3.호출할 때 마다 객체를 생성
        MemberService memberService3 = appConfig.memberService();
        // 참조값이 다 다른 것을 확인할 수 있다.
        // 메모리 낭비가 너무 심해질 수 있다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService3 = " + memberService3);

        //memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴 적용한 객체 호출")
    void singletonServiceTest(){

        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        //참조 값이 같은 것을 확인할 수 있다.
        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        Assertions.assertThat(instance1).isSameAs(instance2);

    }
}
