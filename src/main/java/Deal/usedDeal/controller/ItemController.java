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
    @GetMapping("/items/new")
    public String createItemForm(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);
        return "/items/createForm";
    }

    //item 등록
    @PostMapping("items/new")
    public String createItem(@Valid @ModelAttribute("item") ItemForm form , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "items/new";
        }

        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        itemService.saveItem(book);

        return "redirect:/";
    }


    @GetMapping("/items")
    public String addForm(Model model) {
        List<Item> items = itemService.findItems();
        //로그인 여부 체크
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String editItemForm(@PathVariable Long itemId,Model model){

        Item findItem = itemService.findOne(itemId);

        ItemForm item = new ItemForm();
        item.setName(findItem.getName());
        item.setPrice(findItem.getPrice());
        item.setStockQuantity(findItem.getStockQuantity());
        item.setAuthor(findItem.getAuthor());
        item.setIsbn(findItem.getIsbn());

        model.addAttribute("item",item);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String editItem(@PathVariable Long itemId ,@Valid @ModelAttribute("item") ItemForm form,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.reject("바인딩 에러 발생");
        }
        itemService.updateItem(itemId,form.getName(),form.getPrice(),form.getStockQuantity());
        return "redirect:/items";
    }

}
