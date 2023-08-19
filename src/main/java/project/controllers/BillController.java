package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.service.BillService;
import project.service.PeopleService;

@RestController
public class BillController {
    private BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping(value = "/importBill", params = {"firstNumber","secondNumber","date","sum","month"})
    public @ResponseBody String importBill(@RequestParam(value = "firstNumber") String firstNumber,
                                           @RequestParam(value = "secondNumber") String secondNumber,
                                           @RequestParam(value = "date") String date,
                                           @RequestParam(value = "sum") Double sum,
                                           @RequestParam(value = "month") int month) {
        return this.billService.importBill(firstNumber, secondNumber, date, sum, month);
    }
    //         http://localhost:8080/importBill?firstNumber=&secondNumber=&date=&sum=&month=


    @RequestMapping(value = "/findAllBillsForThisYear")
    public @ResponseBody String findAllBillsForThisYear() {
        return this.billService.findAllBillsForThisYear();
    }
    //           http://localhost:8080/findAllBillsForThisYear

    @RequestMapping(value = "/sumForYear",params = {"year"})
    public @ResponseBody String sumForYear(@RequestParam(value = "year") String year) {
        return this.billService.calculateAllBillsForYears(year);
    }
    //           http://localhost:8080/sumForYear?year=
}
