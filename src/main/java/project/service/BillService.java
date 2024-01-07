package project.service;

import project.entity.Bill;

import java.util.List;

public interface BillService {

    String importBill(String firstNumber, String secondNumber, String date, Double sum, int month);


    String calculateAllBillsForYearsRest(String year);

    List<Bill> calculateAllBillsForYearsWeb(String year);

    String findAllBillsForThisYearRest();
    List<Bill> findAllBillsWeb();
}
