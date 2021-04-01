package com.inmartech.SAPMuleWeb.Service;

import com.inmartech.SAPMuleWeb.Exception.ResourceNotFoundException;
import com.inmartech.SAPMuleWeb.Model.HandbookOne;
import com.inmartech.SAPMuleWeb.Repository.HandbookOneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HandbookOneService {
    @Autowired
    private HandbookOneRepository handbookOneRepository;

    public List<HandbookOne> getAllHandbooks() {
        return this.handbookOneRepository.findAll();
    }

    public ResponseEntity<HandbookOne> getHandbookById(@PathVariable(value = "id") Long handbookId) throws ResourceNotFoundException {
        HandbookOne handbookOne = handbookOneRepository.findById(handbookId)
                .orElseThrow(() -> new ResourceNotFoundException("Handbook not found for this id :: " + handbookId));
        return ResponseEntity.ok().body(handbookOne);
    }

    public HandbookOne getHandbookByName(String handbookName) {
        for (HandbookOne handbookOne : this.handbookOneRepository.findAll()) {
            if(handbookOne.getName().equals(handbookName)) {
                return handbookOne;
            }
        }
        return null;
    }

    public HandbookOne createHandbook(@RequestBody HandbookOne handbookOne) {
        return this.handbookOneRepository.save(handbookOne);
    }

    public ResponseEntity<HandbookOne> updateHandbook(@PathVariable(value = "id") Long handbookId, @RequestBody HandbookOne handbookOneDetails) throws ResourceNotFoundException {
        HandbookOne handbookOne = handbookOneRepository.findById(handbookId)
                .orElseThrow(() -> new ResourceNotFoundException("Handbook not found for this id :: " + handbookId));

        handbookOne.setName(handbookOneDetails.getName());
        handbookOne.setHandbookOneDataList(handbookOneDetails.getHandbookOneDataList());

        return ResponseEntity.ok(this.handbookOneRepository.save(handbookOne));
    }

    public Map<String, Boolean> deleteHandbook(@PathVariable(value = "id") Long handbookId) throws ResourceNotFoundException {
        HandbookOne handbookOne = handbookOneRepository.findById(handbookId)
                .orElseThrow(() -> new ResourceNotFoundException("Handbook not found for this id :: " + handbookId));

        this.handbookOneRepository.delete(handbookOne);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return response;
    }
}
