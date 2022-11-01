package Deal.usedDeal.api;


import Deal.usedDeal.domain.Address;
import Deal.usedDeal.domain.Member;
import Deal.usedDeal.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {
    private final MemberService memberService;

    //유저 생성
    @PostMapping("/users")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request) {

        Member member = new Member();
        member.setName(request.getName());
        member.setAddress(request.getAddress());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id, member.getName(),member.getAddress());

    }

    //유저 조회
    @GetMapping("/users")
    public List<Member> findMemberV1(){
        List<Member> users = memberService.findMembers();
        return users;
    }

    //특정유저 조회
    @GetMapping("/users/{id}")
    public Member findOneMember(@PathVariable Long id){
        Member users = memberService.findOne(id);
        return users;
    }

    //유저 수정
    @PutMapping("/users/{id}")
    public UpdateMemberResponse updateMember(@PathVariable Long id, @RequestBody @Valid UpdateMemberRequest request){
        memberService.update(id,request.getName(),request.getAddress());
        Member user = memberService.findOne(id);
        return new UpdateMemberResponse(user.getId(),user.getName(),user.getAddress());
    }


    //특정유저 삭제
    @DeleteMapping("/users/{id}")
    public void deleteMember(@PathVariable Long id){
        memberService.delete(id);
    }


    //----------------------------메서드---------------------------------------------------------

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }


    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
        private Address address;
    }

    @Data
    static class UpdateMemberRequest{
        private String name;
        private Address address;
    }

    @Data
    static class CreateMemberRequest{
        private String name;
        private Address address;
    }
    @Data
    static class CreateMemberResponse {
        private Long id;
        private String name;

        private Address address;

        public CreateMemberResponse(Long id,String name,Address address) {
            this.id = id;
            this.name = name;
            this.address=address;
        }
        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

















//      잘 모르겠음
//    //유저 조회v2  --> 엔티티를 별도의 dto로 변환. 엔티티 노출x
//    @GetMapping("/users")
//    public List<Member> findMemberV2(){
//        List<Member> users = memberService.findMembers();
//
//        List<MemberDto> collect = users.stream()
//                .map(m->new MemberDto(m.getName()))
//                .collect(Collectors.toList());
//
//        return new Result(collect);
//    }



//    //유저 생성
//    @PostMapping("/users")
//    public CreateMemberResponse saveMember(@RequestBody @Valid Member member) {
//        Long id = memberService.join(member);
//        return new CreateMemberResponse(id, member.getName());
//    }
}
