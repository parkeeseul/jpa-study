package com.seul.jpa.study.controller;

import com.seul.jpa.study.domain.Member;
import com.seul.jpa.study.domain.Order;
import com.seul.jpa.study.domain.item.Item;
import com.seul.jpa.study.repository.OrderSearch;
import com.seul.jpa.study.service.ItemService;
import com.seul.jpa.study.service.MemberService;
import com.seul.jpa.study.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "orders/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") long memberId,
                        @RequestParam("itemId") long itemId,
                        @RequestParam("count") int count) {
        log.error("TEST : " + orderService.order(memberId, itemId, count));
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "orders/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
