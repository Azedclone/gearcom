package com.prm392.gearcom.api.model;

import com.prm392.gearcom.model.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillDetailBody {
    @NotNull
    private int productId;
    @NotNull
    private int quantity;
}
