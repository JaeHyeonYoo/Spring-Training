package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOptionTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        // Member 는 Spring Bean으로 등록되어 있지 않기 때문에 @Autowired로 주입을 할 수 없다
        //그렇기 때문에 required = false 설정을 통해 메서드 자체가 호출되지 않는다.
        @Autowired(required = false)
        public void noBean1(Member member){
            System.out.println("member = " + member);
        }

        //@Nullable 을 통해 주입할 대상이 없으면 null이 호출된다.
        @Autowired
        public void noBean2(@Nullable Member member){
            System.out.println("member = " + member);
        }
        //Optional<>을 통해 주입 대상이 없으면 Option.empty 가 호출된다.
        @Autowired
        public void noBean3(Optional<Member> member){
            System.out.println("member = " + member);
        }
    }
}
