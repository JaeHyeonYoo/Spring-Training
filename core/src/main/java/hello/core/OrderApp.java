package hello.core;

import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        OrderService orderService = appConfig.orderService();   //주문을 하기 위함
//        MemberService memberService = appConfig.memberService();  //회원가입을 위함

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);   //스피링 컨테이너에 저장될 때 @Bean이 붙은 메서드명으로 등록이된다. 임의로 설정도 가능
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);


        Long memberId = 1L;
        Member member = new Member(memberId,"memberA",Grade.VIP);  // 회원가입 정보입력
        memberService.join(member); //회원가입 진행

        Order order = orderService.createOrder(member.getId(), "아바라",10000);  //주문정보 전달

        System.out.println("주문정보 = " + order);
    }
}
