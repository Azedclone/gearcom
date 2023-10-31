package com.prm392.gearcom.service;

import com.prm392.gearcom.api.model.BillBody;
import com.prm392.gearcom.model.Bill;
import com.prm392.gearcom.model.BillDetail;
import com.prm392.gearcom.model.User;
import com.prm392.gearcom.repository.BillRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private final BillRepository billRepository;

    public BillService(BillRepository _billRepository
                       ) {
        this.billRepository = _billRepository;
//        this.billDetailService = _billDetailService;
    }

    public Bill createBill(@AuthenticationPrincipal User user) {
        Bill bill = new Bill();
        bill.setUser(user);

        return billRepository.save(bill);
    }

    public Bill getBillById(int id) {
        Optional<Bill> optionalBill = billRepository.findById(id);
        return optionalBill.orElse(null);
    }
}
