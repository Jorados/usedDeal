package Deal.usedDeal;


import Deal.usedDeal.domain.Member;
import Deal.usedDeal.repository.MemberRepository;
import Deal.usedDeal.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.aspectj.bridge.MessageUtil.fail;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class MemberServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("Jo22");
        //when
        Long saveId = memberService.join(member);
        //then
        Assertions.assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test
    public void 중복회원검증() throws Exception{

        //given
        Member member = new Member();
        member.setName("jojo");

        Member member1 = new Member();
        member.setName("jojo2");

        //when
        memberService.join(member);
        memberService.join(member1);

        //then
        fail("예외 발생");


    }
}
