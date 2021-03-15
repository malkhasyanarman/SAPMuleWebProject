package com.inmartech.SAPMuleWeb.Service;

import com.inmartech.SAPMuleWeb.Exception.ResourceNotFoundException;
import com.inmartech.SAPMuleWeb.Model.Handbook;
import com.inmartech.SAPMuleWeb.Repository.HandbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HandbookService {
    @Autowired
    private HandbookRepository handbookRepository;

    public List<Handbook> getAllHandbooks() {
        return this.handbookRepository.findAll();
    }

    public ResponseEntity<Handbook> getHandbookById(@PathVariable(value = "id") Long handbookId) throws ResourceNotFoundException {
        Handbook handbook = handbookRepository.findById(handbookId)
                .orElseThrow(() -> new ResourceNotFoundException("Handbook not found for this id :: " + handbookId));
        return ResponseEntity.ok().body(handbook);
    }

    public Handbook createHandbook(@RequestBody Handbook handbook) {
        return this.handbookRepository.save(handbook);
    }

    public ResponseEntity<Handbook> updateHandbook(@PathVariable(value = "id") Long handbookId, @RequestBody Handbook handbookDetails) throws ResourceNotFoundException {
        Handbook handbook = handbookRepository.findById(handbookId)
                .orElseThrow(() -> new ResourceNotFoundException("Handbook not found for this id :: " + handbookId));

        handbook.setName(handbookDetails.getName());
        handbook.setTitle(handbookDetails.getTitle());

        return ResponseEntity.ok(this.handbookRepository.save(handbook));
    }

    public Map<String, Boolean> deleteHandbook(@PathVariable(value = "id") Long handbookId) throws ResourceNotFoundException {
        Handbook handbook = handbookRepository.findById(handbookId)
                .orElseThrow(() -> new ResourceNotFoundException("Handbook not found for this id :: " + handbookId));

        this.handbookRepository.delete(handbook);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return response;
    }
}
