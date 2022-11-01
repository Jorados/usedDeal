package Deal.usedDeal.api;

import Deal.usedDeal.domain.Order;
import Deal.usedDeal.repository.OrderRepository;
import Deal.usedDeal.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("users-orders")
    public List<Order> orders(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        //하이버네이트에서 지연로딩에의한 프록시객체는 초기화 된 객체만 나타낸다.
        for (Order order : all) {
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기화
        }
        return all;
    }


}
