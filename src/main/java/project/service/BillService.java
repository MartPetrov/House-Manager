package project.service;

import project.entity.Bill;
import project.entity.TypeOfBill;

import java.util.List;

public interface BillService {

    String importBill(String firstNumber, String secondNumber, String date, Double sum, int month, TypeOfBill typeOfBill);


    String calculateAllBillsForYearsRest(String year);


    String findAllBillsForThisYearRest();
    List<Bill> findAllBillsWeb();
}
