package Deal.usedDeal.service;


import Deal.usedDeal.domain.Delivery;
import Deal.usedDeal.domain.Member;
import Deal.usedDeal.domain.Order;
import Deal.usedDeal.domain.OrderItem;
import Deal.usedDeal.domain.item.Item;
import Deal.usedDeal.repository.ItemRepository;
import Deal.usedDeal.repository.MemberRepository;
import Deal.usedDeal.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private MemberRepository memberRepository;
    private ItemRepository itemRepository;


    @Transactional
    public Long order(Long itemId,Long memberId,int count){

        //엔티티조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);

        //주문
        Order order = Order.createOrder(member,delivery,orderItem);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
