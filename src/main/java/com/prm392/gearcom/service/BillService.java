package com.prm392.gearcom.service;

import com.prm392.gearcom.api.model.BillBody;
import com.prm392.gearcom.api.model.BillDetailBody;
import com.prm392.gearcom.model.Bill;
import com.prm392.gearcom.model.BillDetail;
import com.prm392.gearcom.model.Product;
import com.prm392.gearcom.model.User;
import com.prm392.gearcom.repository.BillDetailRepository;
import com.prm392.gearcom.repository.BillRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final ProductService productService;
    private final BillDetailRepository billDetailRepository;

    public BillService(BillRepository _billRepository, ProductService productService, BillDetailRepository billDetailRepository) {
        this.billRepository = _billRepository;
        this.productService = productService;
        this.billDetailRepository = billDetailRepository;
    }

    @Transactional
    public void createBill(BillBody body, @AuthenticationPrincipal User user) {
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setTotalPrice(body.getBill().getTotalPrice());
        billRepository.save(bill);
        List<BillDetail> billDetails = new ArrayList<>();
        if (bill != null) {
            for (BillDetailBody billDetailBody : body.getBillDetailBodies()) {
                billDetails.add(createBillDetail(bill, billDetailBody));
            }
        }
        billDetailRepository.saveAll(billDetails);
    }
    public BillDetail createBillDetail(Bill bill, BillDetailBody billDetailBody) {
        BillDetail billDetail = new BillDetail();
        billDetail.setBill(bill);
        billDetail.setQuantity(billDetailBody.getQuantity());
        Product product = productService.getProductById(billDetailBody.getProductId());
        billDetail.setProduct(product);

        return billDetail;
    }

    public Bill getBillById(int id) {
        Optional<Bill> optionalBill = billRepository.findById(id);
        return optionalBill.orElse(null);
    }
}
