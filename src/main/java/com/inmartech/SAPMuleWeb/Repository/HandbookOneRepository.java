package com.inmartech.SAPMuleWeb.Repository;

import com.inmartech.SAPMuleWeb.Model.HandbookOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandbookOneRepository extends JpaRepository<HandbookOne, Long> {

}
