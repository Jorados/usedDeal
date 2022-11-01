package Deal.usedDeal.service;


import Deal.usedDeal.domain.Address;
import Deal.usedDeal.domain.Member;
import Deal.usedDeal.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;
    private EntityManager em;

    //회원가입
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //중복회원 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //로그인아이디 검증
    private void UniquieLoginId(String id){
        Member findMember = memberRepository.findByLoginId(id);
        if(findMember != null){
            throw new IllegalStateException("이미 존재하는 id입니다.");
        }
    }


    //회원 전체찾기
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //id로 회원찾기
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    //회원 삭제
    @Transactional
    public void delete(Long userId) {
        memberRepository.delete(userId);
    }

    //회원 수정
    @Transactional
    public void update(Long id, String name, Address address){
        Member member = memberRepository.findOne(id);
        member.setName(name);
        member.setAddress(address);
    }




}
