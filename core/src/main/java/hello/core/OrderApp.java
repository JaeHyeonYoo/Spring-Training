package hello.core;

import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        OrderService orderService = appConfig.orderService();   //주문을 하기 위함
        MemberService memberService = appConfig.memberService();  //회원가입을 위함

        Long memberId = 1L;
        Member member = new Member(memberId,"memberA",Grade.VIP);  // 회원가입 정보입력
        memberService.join(member); //회원가입 진행

        Order order = orderService.createOrder(member.getId(), "아바라",10000);  //주문정보 전달

        System.out.println("주문정보 = " + order);
    }
}
