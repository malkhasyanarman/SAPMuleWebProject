package com.inmartech.SAPMuleWeb.Controller;

import com.inmartech.SAPMuleWeb.Exception.ResourceNotFoundException;
import com.inmartech.SAPMuleWeb.Model.HandbookOne;
import com.inmartech.SAPMuleWeb.Model.HandbookOneData;
import com.inmartech.SAPMuleWeb.Service.HandbookOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/handbookOne")
public class HandbookOneController {

    @Autowired
    private HandbookOneService handbookOneService;

    @GetMapping("getAllHandbooks")
    public List<HandbookOne> getAllHandbooks() {
        return this.handbookOneService.getAllHandbooks();
    }

    @GetMapping("getHandbookById/{id}")
    public ResponseEntity<HandbookOne> getHandbookById(@PathVariable(value = "id") Long handbookId) throws ResourceNotFoundException {
        return handbookOneService.getHandbookById(handbookId);
    }

    @PostMapping("createHandbook")
    public void createHandbook(HttpServletResponse response, @ModelAttribute("handbook") HandbookOne handbookOne) throws IOException {
        this.handbookOneService.createHandbook(handbookOne);
        response.sendRedirect("/handbook");
    }

    @PostMapping("createHandbookData/{id}")
    public void createHandbookData(HttpServletResponse response, @ModelAttribute("handbook") HandbookOneData handbookOneData, @PathVariable(value = "id") Long handbookId) throws IOException, ResourceNotFoundException {
        HandbookOne handbookForUpdate = this.handbookOneService.getHandbookById(handbookId).getBody();
        handbookForUpdate.getHandbookOneDataList().add(handbookOneData);

        this.handbookOneService.createHandbook(handbookForUpdate);
        response.sendRedirect("/handbook/handbookOne/" + handbookId);
    }

    @PostMapping("updateHandbookData/{id}")
    public void updateHandbookData(HttpServletResponse response, @ModelAttribute("handbook") HandbookOneData handbookOneData, @PathVariable(value = "id") Long handbookId) throws IOException, ResourceNotFoundException {
        HandbookOne handbookForUpdate = this.handbookOneService.getHandbookById(handbookId).getBody();

        for (HandbookOneData oneData : handbookForUpdate.getHandbookOneDataList()) {
            if(oneData.getId() == handbookOneData.getId()) {
                oneData.setLegalEntity(handbookOneData.getLegalEntity());
                oneData.setStorageLocation(handbookOneData.getStorageLocation());
                oneData.setCustomer(handbookOneData.getCustomer());
                oneData.setAgency(handbookOneData.getAgency());
                break;
            }
        }

        this.handbookOneService.createHandbook(handbookForUpdate);
        response.sendRedirect("/handbook/handbookOne/" + handbookId);
    }

    @PutMapping("updateHandbook/{id}")
    public void updateHandbook(HttpServletResponse response, @PathVariable(value = "id") Long handbookId, @RequestBody HandbookOne handbookOneDetails) throws ResourceNotFoundException, IOException {
        handbookOneService.updateHandbook(handbookId, handbookOneDetails);
        response.sendRedirect("/handbook");
    }

    @PostMapping("deleteHandbook/{id}")
    public void deleteHandbook(HttpServletResponse response, @PathVariable(value = "id") Long handbookId) throws ResourceNotFoundException, IOException {
        handbookOneService.deleteHandbook(handbookId);
        response.sendRedirect("/handbook");
    }

    @PostMapping("deleteHandbookData/{id}/{dataId}")
    public void deleteHandbookData(HttpServletResponse response, @PathVariable(value = "id") Long handbookId, @PathVariable(value = "dataId") Long handbookDataId) throws ResourceNotFoundException, IOException {
        HandbookOne handbookOne = handbookOneService.getHandbookById(handbookId).getBody();
        for (HandbookOneData handbookOneData : new ArrayList<>(handbookOne.getHandbookOneDataList())) {
            if(handbookOneData.getId() == handbookDataId) {
                handbookOne.getHandbookOneDataList().remove(handbookOneData);
            }
        }

        handbookOneService.createHandbook(handbookOne);
        response.sendRedirect("/handbook/handbookOne/" + handbookId);
    }

}
