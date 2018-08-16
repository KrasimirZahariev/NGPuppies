package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillService extends JpaRepository<Bill, Integer> {

}
