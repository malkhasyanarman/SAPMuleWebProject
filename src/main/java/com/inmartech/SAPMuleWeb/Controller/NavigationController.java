package com.inmartech.SAPMuleWeb.Controller;

import com.inmartech.SAPMuleWeb.Exception.ResourceNotFoundException;
import com.inmartech.SAPMuleWeb.Model.Handbook;
import com.inmartech.SAPMuleWeb.Service.HandbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController {

    @Autowired
    private HandbookService handbookService;

    @GetMapping("/handbook")
    public String getHandBook(Model model) {
        model.addAttribute("activePage", "handbook");
        model.addAttribute("handbooks", handbookService.getAllHandbooks());
        return "handbook";
    }

    @GetMapping("/handbook/createHandbook")
    public String getCreateHandBook(Model model) {
        model.addAttribute("activePage", "handbook");
        model.addAttribute("handbook", new Handbook());
        return "createHandbook";
    }

    @RequestMapping("/handbook/editHandbook/{id}")
    public ModelAndView getEditHandBook(Model model, @PathVariable(name = "id") long handbookId) throws ResourceNotFoundException {
        ModelAndView modelAndView = new ModelAndView("createHandbook");
        Handbook handbook = handbookService.getHandbookById(handbookId).getBody();
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
