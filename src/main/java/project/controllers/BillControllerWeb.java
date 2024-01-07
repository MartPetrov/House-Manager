package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import project.service.BillService;
@Controller
public class BillControllerWeb {

    @Autowired
    private final BillService billService;

    @Autowired
    public BillControllerWeb(BillService billService) {
        this.billService = billService;
    }


    @GetMapping("/bills")
    public String findAllBills(Model model) {
        model.addAttribute("getAllBills",billService.findAllBillsWeb());
        return "bills.html";
    }
}
