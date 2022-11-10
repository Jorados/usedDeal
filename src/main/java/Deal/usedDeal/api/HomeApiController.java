package Deal.usedDeal.api;

import Deal.usedDeal.domain.Member;
import Deal.usedDeal.repository.MemberRepository;
import Deal.usedDeal.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

//@Slf4j
//@RestController
//@RequiredArgsConstructor
//public class HomeApiController {
//
//    private final MemberRepository memberRepository;
//
//    @GetMapping("/")
//    public String HomeLogin(
//            @SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember, Model model){
//
//        if(loginMember ==null){
//            return null;
//        }
//
//        model.addAttribute("member",loginMember);
//        return "loginHome";
//    }
//
//}
