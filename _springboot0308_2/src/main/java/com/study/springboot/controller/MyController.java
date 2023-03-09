package com.study.springboot.controller;

import com.study.springboot.entity.Cart;
import com.study.springboot.repository.CartRepository;
import com.study.springboot.repository.MyEntityManager;
import com.study.springboot.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {
    private final MyEntityManager em;
    private final CartRepository cartRepository;

    @GetMapping("/create")
    @ResponseBody
    public String create() {
        String str = em.create();
        return str;
    }

    @GetMapping("/update")
    @ResponseBody
    public String update() {
        String str = em.update();
        return str;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Person> list() {
        List<Person> list = em.selectAll();
        return list;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delete() {
        String str = em.delete();
        return str;
    }

    @GetMapping("/cart")
    @ResponseBody
    public String cart(){
        String str = em.putInCart();
        return str;
    }

    @GetMapping("/getCart")
    @ResponseBody
    public Cart getCart(){
        Cart cart = em.getCart();
        System.out.println("getCart cart ===> " + cart);
        return cart;
    }

    @GetMapping("/")
    public String index(){
        return "view/index";
    }

    @GetMapping("page2")
    public String listAction(Model model, @PageableDefault(page=0,size=10) Pageable pageable){
        List<Cart> list = cartRepository.findAllByOrderByIdDesc();
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()),list.size());
        final Page<Cart> page = new PageImpl<>(list.subList(start,end),pageable,list.size());
        model.addAttribute("list",page);
        return "view/list";
    }

    @GetMapping("/detail")
    public String detail(Long id, Model model){
        Cart cart = em.getCart2(id);
        System.out.println("===>"+cart.getProducts());
        model.addAttribute("list", cart.getProducts());
        return "view/detail";
    }

    @GetMapping("/insert1")
    public String insert1(){
        String str = em.putInCart();
        System.out.println("===>"+str);
        return "redirect:/page2";
    }
}
