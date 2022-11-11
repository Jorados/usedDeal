package Deal.usedDeal.controller;


import Deal.usedDeal.controller.form.ItemForm;
import Deal.usedDeal.domain.item.Book;
import Deal.usedDeal.domain.item.Item;
import Deal.usedDeal.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;


    //item 조회
    @GetMapping("/items")
    public String createItemForm(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);

        return "/items/createForm";
    }

    @GetMapping("/items/add")
    public String addForm(Model model) {
        //로그인 여부 체크
        model.addAttribute("item", new Book());
        return "items/new";
    }


    //item 등록
    @PostMapping("items/new")
    public String createItem(@Valid @ModelAttribute("item") ItemForm form , BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "items/new";
        }

        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getQuantity());
        itemService.saveItem(book);

        redirectAttributes.addAttribute("itemId",book.getId());

        return "redirect:items/{itemId}/";
    }

    //item 번호 클릭
    @GetMapping("/{itemId}")
    public String ClickItem(@PathVariable("itemId") long itemId,Model model){

        Item findItem = itemService.findOne(itemId);
        model.addAttribute("item",findItem);

        return "items/item";
    }


}
