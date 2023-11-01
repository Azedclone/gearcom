package com.prm392.gearcom.service;

import com.prm392.gearcom.model.Bill;
import com.prm392.gearcom.model.User;
import com.prm392.gearcom.repository.BillRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService {
    private final BillRepository billRepository;

    public BillService(BillRepository _billRepository) {
        this.billRepository = _billRepository;
    }

    public Bill createBill(Bill bill, @AuthenticationPrincipal User user) {
        bill.setUser(user);

        return billRepository.save(bill);
    }

    public Bill getBillById(int id) {
        Optional<Bill> optionalBill = billRepository.findById(id);
        return optionalBill.orElse(null);
    }
}
