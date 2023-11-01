package com.prm392.gearcom.api.controller.bill;

import com.prm392.gearcom.model.Bill;
import com.prm392.gearcom.model.User;
import com.prm392.gearcom.service.BillService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {

    private final BillService billService;

    public BillController(BillService _billService) {
        this.billService = _billService;
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill, @AuthenticationPrincipal User user) {
        return billService.createBill(bill, user);
    }

}
