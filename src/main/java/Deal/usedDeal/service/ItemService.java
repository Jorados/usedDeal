package Deal.usedDeal.service;


import Deal.usedDeal.domain.item.Item;
import Deal.usedDeal.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    @Transactional
    public void updateItem(Long itemId,String name,int price,int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }
}
