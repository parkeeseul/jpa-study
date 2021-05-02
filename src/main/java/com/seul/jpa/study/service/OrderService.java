package com.seul.jpa.study.service;

import com.seul.jpa.study.domain.Delivery;
import com.seul.jpa.study.domain.Member;
import com.seul.jpa.study.domain.Order;
import com.seul.jpa.study.domain.OrderItem;
import com.seul.jpa.study.domain.item.Item;
import com.seul.jpa.study.repository.ItemRepository;
import com.seul.jpa.study.repository.MemberRepository;
import com.seul.jpa.study.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     **/
    public Long order(long memberId, long itemId, int count) {

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = Delivery.createDelivery(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order); // CascadeType.ALL 로 인해 orderItem, delivery 를 따로 persist 해주지 않아도 order 가 persist 될때 자동으로 persist 된다.

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
