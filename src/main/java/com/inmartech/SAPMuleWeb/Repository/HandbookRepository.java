package com.inmartech.SAPMuleWeb.Repository;

import com.inmartech.SAPMuleWeb.Model.Handbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandbookRepository extends JpaRepository<Handbook, Long> {

}
