package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository(); // MemoryMemberRepository 만들기
        memberService = new MemberService(memberRepository);
        // 외부에서 만든 일정한 레포지토리를 넣어줌 (Dependency Injection)
        // 이렇게 안하면 서로 다른 레포지토리를 이용하는 불상사가 발생할 수도 있음
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given - 어떤 상황이 주어져서 (hello 라는 이름을 가진 멤버를 만듦)
        Member member = new Member();
        member.setName("박수아");

        //when - 이걸 실행했을 때 (앞서 만든 멤버의 아이디를 얻음)
        Long saveId = memberService.join(member);

        //then - 이런 결과가 나와야 해 (앞에서 얻은 아이디를 넣은 멤버 객체를 얻었을 때 같은 이름이 나와야 함)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        String name = "박수아";
        Member member1 = new Member();
        member1.setName(name);

        Member member2 = new Member();
        member2.setName(name);

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> memberService.join(member2)
        );

//        try{
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}