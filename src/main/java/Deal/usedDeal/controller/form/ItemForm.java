package Deal.usedDeal.controller.form;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemForm {

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer quantity;
}
