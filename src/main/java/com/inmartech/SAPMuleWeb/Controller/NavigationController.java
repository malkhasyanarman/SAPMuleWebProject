package com.inmartech.SAPMuleWeb.Controller;

import com.inmartech.SAPMuleWeb.Exception.ResourceNotFoundException;
import com.inmartech.SAPMuleWeb.Model.HandbookOne;
import com.inmartech.SAPMuleWeb.Model.HandbookOneData;
import com.inmartech.SAPMuleWeb.Service.HandbookOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NavigationController {

    @Autowired
    private HandbookOneService handbookOneService;

    @GetMapping("/handbook")
    public String getHandBook(Model model) {
        model.addAttribute("activePage", "handbook");

        List<HandbookOne> handbookOneList = handbookOneService.getAllHandbooks();
        model.addAttribute("handbookOneList", handbookOneList);

        return "handbook";
    }

    @GetMapping("/handbook/handbookOne/{handbookId}")
    public String getHandBookById(Model model, @PathVariable(name = "handbookId") Long handbookId) throws ResourceNotFoundException {
        model.addAttribute("activePage", "handbook");
        model.addAttribute("activeTab", handbookId);

        List<HandbookOne> handbookOneList = handbookOneService.getAllHandbooks();
        model.addAttribute("handbookOneList", handbookOneList);

        for (HandbookOne handbookOne : handbookOneList) {
            if(handbookOne.getId() == handbookId) {
                model.addAttribute("currentHandbook", handbookOne);
                model.addAttribute("currentHandbookDataList", handbookOne.getHandbookOneDataList());
            }
        }

        return "handbook";
    }

    @GetMapping("/handbook/handbookOne/createHandbookData/{id}")
    public String getCreateHandBook(Model model, @PathVariable(name = "id") long handbookId) {
        model.addAttribute("activePage", "handbook");
        model.addAttribute("handbookId", handbookId);
        model.addAttribute("handbookData", new HandbookOneData());
        return "createHandbook";
    }

    @RequestMapping("/handbook/editHandbook/{id}")
    public ModelAndView getEditHandBook(Model model, @PathVariable(name = "id") long handbookId) throws ResourceNotFoundException {
        ModelAndView modelAndView = new ModelAndView("createHandbook");
        HandbookOne handbook = handbookOneService.getHandbookById(handbookId).getBody();
        modelAndView.addObject(handbook);
        model.addAttribute("activePage", "handbook");
        return modelAndView;
    }

    @GetMapping("/schedule")
    public String getSchedule(Model model) {
        model.addAttribute("activePage", "schedule");
        return "schedule";
    }
}
