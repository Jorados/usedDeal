package Deal.usedDeal.api;


import Deal.usedDeal.domain.Address;
import Deal.usedDeal.domain.Member;
import Deal.usedDeal.service.LoginService;
import Deal.usedDeal.session.SessionConst;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/login")
    public CreateMemberResponse login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return new CreateMemberResponse(loginMember.getId(), loginMember.getName(), loginMember.getAddress());

    }

    @Data
    static class CreateMemberResponse {
        private Long id;
        private String name;

        private Address address;

        public CreateMemberResponse(Long id, String name, Address address) {
            this.id = id;
            this.name = name;
            this.address = address;
        }
    }

}
