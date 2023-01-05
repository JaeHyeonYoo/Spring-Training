package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    @Autowired
    //수정자 의존주입 setter 주입
    //필드의 값을 변경하기 위해 set메서드를 사용한다, 선택 변경의 가능성이 있는 의존관계에 사용한다.
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("2. memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    //생성자는 스프링 빈으로 등록될 때 어쩔 수 없이 의존성 주입이 일어날 수 밖에 없다.
    //생성자가 아닌 수정자 주입 외 다른 주입은 스프링 빈이 등록된 이후 의존성 주입이 일어난다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        System.out.println("1. memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
