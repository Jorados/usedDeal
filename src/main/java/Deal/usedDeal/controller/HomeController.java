package Deal.usedDeal.controller;

import Deal.usedDeal.domain.Member;
import Deal.usedDeal.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String HomeLoginCheck(@SessionAttribute(name= SessionConst.LOGIN_MEMBER,required = false )Member loginMember, Model model){

        log.info("home controller");

        if(loginMember == null){
            return "home";
        }
        model.addAttribute("member",loginMember);
        return "loginHome";
    }
}
