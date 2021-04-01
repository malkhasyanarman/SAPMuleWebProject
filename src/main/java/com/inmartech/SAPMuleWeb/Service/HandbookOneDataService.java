package com.inmartech.SAPMuleWeb.Service;

import com.inmartech.SAPMuleWeb.Exception.ResourceNotFoundException;
import com.inmartech.SAPMuleWeb.Model.HandbookOneData;
import com.inmartech.SAPMuleWeb.Repository.HandbookOneDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Service
public class HandbookOneDataService {

    @Autowired
    private HandbookOneDataRepository handbookOneDataRepository;

    public ResponseEntity<HandbookOneData> getHandbookDataById(@PathVariable(value = "id") Long handbookId) throws ResourceNotFoundException {
        HandbookOneData handbookOneData = handbookOneDataRepository.findById(handbookId)
                .orElseThrow(() -> new ResourceNotFoundException("Handbook not found for this id :: " + handbookId));
        return ResponseEntity.ok().body(handbookOneData);
    }

    public Map<String, Boolean> deleteHandbook(@PathVariable(value = "id") Long handbookDataId) throws ResourceNotFoundException {
        HandbookOneData handbookOne = handbookOneDataRepository.findById(handbookDataId)
                .orElseThrow(() -> new ResourceNotFoundException("Handbook not found for this id :: " + handbookDataId));

        this.handbookOneDataRepository.delete(handbookOne);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return response;
    }
}
