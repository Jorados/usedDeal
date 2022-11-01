package Deal.usedDeal.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private String street;
    private String city;
    private String zipcode;
}
