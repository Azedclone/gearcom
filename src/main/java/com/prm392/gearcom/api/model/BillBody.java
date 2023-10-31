package com.prm392.gearcom.api.model;

import com.prm392.gearcom.model.Bill;
import com.prm392.gearcom.model.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BillBody {
    @NotNull
    private Bill bill;
    @NotNull
    private List<BillDetailBody> billDetailBodies;
}
