package com.prm392.gearcom.repository;

import com.prm392.gearcom.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Override
    Optional<Bill> findById(Integer id);
}
