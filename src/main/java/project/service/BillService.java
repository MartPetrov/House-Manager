package project.service;

public interface BillService {

    String importBill(String firstNumber, String secondNumber, String date, Double sum, int month);


    String calculateAllBillsForYears(String year);

    String findAllBillsForThisYear();
}
