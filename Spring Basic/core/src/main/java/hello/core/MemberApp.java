package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();  //스프링을 사용하기 때문에 필요성이 없어진다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); //스프링에 AppConfig로 등록된 것을 가져온다.
        MemberService memberService = ac.getBean("memberService",MemberService.class);  // memberService 라고 스프링에 등록된 것을 불러온다.

        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        System.out.println(member.getName()); //memberA
        System.out.println(findMember.getName());  //memberA
    }
}
