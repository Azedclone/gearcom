package com.prm392.gearcom.service;

import com.prm392.gearcom.api.model.BillBody;
import com.prm392.gearcom.api.model.BillDetailBody;
import com.prm392.gearcom.model.Bill;
import com.prm392.gearcom.model.BillDetail;
import com.prm392.gearcom.model.Product;
import com.prm392.gearcom.repository.BillDetailRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillDetailService {
    private final BillDetailRepository billDetailRepository;
    private final BillService billService;
    private final ProductService productService;

    public BillDetailService(BillDetailRepository _billDetailRepository,
                             ProductService _productService,
                             BillService _billService) {
        this.billDetailRepository = _billDetailRepository;
        this.productService = _productService;
        this.billService = _billService;
    }

    public BillDetail createBillDetail(Bill bill, BillDetailBody billDetailBody) {
        BillDetail billDetail = new BillDetail();
        billDetail.setBill(bill);
        billDetail.setQuantity(billDetailBody.getQuantity());
        Product product = productService.getProductById(billDetailBody.getProductId());
        billDetail.setProduct(product);

        return billDetail;
    }

    public List<BillDetail> createBillDetails(Bill bill, List<BillDetailBody> billDetailBodies) {
        List<BillDetail> billDetails = new ArrayList<>();
        for (BillDetailBody billDetailBody : billDetailBodies) {
            billDetails.add(createBillDetail(bill, billDetailBody));
        }

        return billDetailRepository.saveAll(billDetails);
    }
}
