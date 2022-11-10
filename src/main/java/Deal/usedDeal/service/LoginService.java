package Deal.usedDeal.service;

import Deal.usedDeal.FailedLoginEx;
import Deal.usedDeal.domain.Member;
import Deal.usedDeal.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    public Member login(String loginId,String password){
        Member findMember = memberRepository.findByLoginId(loginId);
        if(findMember == null){
            throw new FailedLoginEx("존재하지 않는 아이디입니다.");
        }
        if(findMember.getPassword().equals(password)){
            return findMember;
        }else{
            throw new FailedLoginEx("id 와 password 가 일치하지 않습니다.");
        }
    }
}
