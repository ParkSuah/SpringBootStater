package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service // spring 컨테이너에 memberService 등록
public class MemberService {
    private final MemberRepository memberRepository;

//    @Autowired // spring 에서 생성할 때 컨테이너에 생성자 등록하고 동시에 서비스에 레포지토리를 주입(injection)시켜준다.
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
    * 회원가입
    */
    public Long join(Member member){
        // 같은 이름을 가진 회원은 안되도록
        validateDuplicateMember(member); // Optional 로 감싸면 다양한 기능사용 가능 (ifPresent..)
        // findByName 에서 리턴할 때 Optional 로 리턴했기 때문에
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
