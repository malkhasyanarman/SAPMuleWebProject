package com.inmartech.SAPMuleWeb.Controller;

import com.inmartech.SAPMuleWeb.Exception.ResourceNotFoundException;
import com.inmartech.SAPMuleWeb.Model.HandbookOneData;
import com.inmartech.SAPMuleWeb.Service.HandbookOneDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/handbookOneData")
public class HandbookOneDataController {
    @Autowired
    HandbookOneDataService handbookOneDataService;

    @GetMapping("getHandbookDataById/{id}")
    public ResponseEntity<HandbookOneData> getHandbookDataById(@PathVariable(value = "id") Long handbookId) throws ResourceNotFoundException {
        return handbookOneDataService.getHandbookDataById(handbookId);
    }

    @PostMapping("deleteHandbook/{id}")
    public void deleteHandbook(@PathVariable(value = "id") Long handbookId) throws ResourceNotFoundException, IOException {
        handbookOneDataService.deleteHandbook(handbookId);
    }
}
