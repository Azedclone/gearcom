package com.prm392.gearcom.repository;

import com.prm392.gearcom.model.Bill;
import com.prm392.gearcom.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(Integer id);

    Cart findByUserIdAndProductId(Integer userId, Integer productId);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM iqi5186tmljsc2ji.cart WHERE (user_id = :userId)", nativeQuery = true)
    int deleteByUserId(Integer userId);

}
