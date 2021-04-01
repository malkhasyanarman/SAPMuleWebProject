package com.inmartech.SAPMuleWeb.Repository;

import com.inmartech.SAPMuleWeb.Model.HandbookOneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandbookOneDataRepository extends JpaRepository<HandbookOneData, Long> {
}
