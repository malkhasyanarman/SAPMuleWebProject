package com.inmartech.SAPMuleWeb.Controller;

import com.inmartech.SAPMuleWeb.Exception.ResourceNotFoundException;
import com.inmartech.SAPMuleWeb.Model.Handbook;
import com.inmartech.SAPMuleWeb.Service.HandbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequestMapping("api/v1/")
public class HandbookController {

    @Autowired
    private HandbookService handbookService;

    @GetMapping("getAllHandbooks")
    public List<Handbook> getAllHandbooks() {
        return this.handbookService.getAllHandbooks();
    }

    @GetMapping("getHandbookById/{id}")
    public ResponseEntity<Handbook> getHandbookById(@PathVariable(value = "id") Long handbookId) throws ResourceNotFoundException {
        return handbookService.getHandbookById(handbookId);
    }

    @PostMapping("createHandbook")
    public String createHandbook(@ModelAttribute("handbook") Handbook handbook) {
        this.handbookService.createHandbook(handbook);
        return "redirect:/handbook";
    }

    @PutMapping("updateHandbook/{id}")
    public String updateHandbook(@PathVariable(value = "id") Long handbookId, @RequestBody Handbook handbookDetails) throws ResourceNotFoundException {
        handbookService.updateHandbook(handbookId, handbookDetails);
        return "redirect:/handbook";
    }

    @PostMapping("deleteHandbook/{id}")
    public String deleteHandbook(@PathVariable(value = "id") Long handbookId) throws ResourceNotFoundException {
        handbookService.deleteHandbook(handbookId);
        return "redirect:/handbook";
    }

}
