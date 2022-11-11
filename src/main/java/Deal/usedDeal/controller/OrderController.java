package Deal.usedDeal.controller;

import Deal.usedDeal.domain.Member;
import Deal.usedDeal.domain.Order;
import Deal.usedDeal.domain.item.Item;
import Deal.usedDeal.repository.OrderSearch;
import Deal.usedDeal.service.ItemService;
import Deal.usedDeal.service.MemberService;
import Deal.usedDeal.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;
    private final MemberService memberService;

    @GetMapping("/order")
    public String OrderItemForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();
        model.addAttribute("members",members);
        model.addAttribute("items",items);
        return"order/orderForm";
    }

    @PostMapping("/order")
    public String OrderItem(@RequestParam("memberId") Long memberId,
                            @RequestParam("itemId") Long itemId,
                            @RequestParam("count") int count){

        orderService.order(memberId,itemId,count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String OrderList(@ModelAttribute("orderSearch") OrderSearch orderSearch,Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders",orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String OrderCancel(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
