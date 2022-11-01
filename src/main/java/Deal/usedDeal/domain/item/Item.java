package Deal.usedDeal.domain.item;


import Deal.usedDeal.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void addStock(int quantity){
        this.stockQuantity +=quantity;
    }

    public void removeStock(int quantity){
        int restStock=this.stockQuantity-quantity;
        if(restStock<0){
            throw new IllegalStateException("재고 부족");
        }
        this.stockQuantity=restStock;
    }
}
