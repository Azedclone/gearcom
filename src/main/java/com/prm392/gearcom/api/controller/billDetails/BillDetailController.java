package com.prm392.gearcom.api.controller.billDetails;

import com.prm392.gearcom.api.model.BillBody;
import com.prm392.gearcom.model.Bill;
import com.prm392.gearcom.model.BillDetail;
import com.prm392.gearcom.service.BillDetailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/billDetail")
public class BillDetailController {

    private final BillDetailService billDetailService;

    public BillDetailController(BillDetailService _billDetailService) {
        this.billDetailService = _billDetailService;
    }

    @PostMapping
    public List<BillDetail> createBillDetails(@RequestBody BillBody body) {
        return billDetailService.createBillDetails(body.getBillId(), body.getBillDetailBodies());
    }

}
