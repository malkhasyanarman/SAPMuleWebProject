package com.inmartech.SAPMuleWeb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/handbook")
    public String getHandBook() {
        return "handbook";
    }

    @GetMapping("/schedule")
    public String getSchedule() {
        return "schedule";
    }
}
