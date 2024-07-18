package project.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.entity.Bill;
import project.enums.TypeOfBill;
import project.repositories.BillsRepository;
import project.service.BillService;

import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static project.constans.Messages.SUCCESSFUL_IMPORT_BILL;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillsRepository billsRepository;


    @Override
    public String importBill(String firstNumber, String secondNumber, String date, Double sum, int month, TypeOfBill typeOfBill) {
        TypeOfBill[] values = TypeOfBill.values();
        if (Arrays.stream(values).noneMatch(type -> type.equals(typeOfBill))) {
            return "Invalid type of bill";
        }
        Bill bill = new Bill(firstNumber, secondNumber, date, sum, month,typeOfBill);

        this.billsRepository.save(bill);
        return SUCCESSFUL_IMPORT_BILL + " for month " + month;
    }

    @Override
    public String findAllBillsForThisYearRest() {
        List<Bill> allBills =
                this.billsRepository.findAll().stream().filter(bill -> bill.getDate().endsWith(String.valueOf(Year.now().getValue())))
                        .sorted(Comparator.comparingInt(Bill::getMonth))
                        .toList();
        StringBuilder sb = new StringBuilder();
        allBills.forEach(b -> sb.append(b.toStringRest()));
        return sb.toString();
    }



    @Override
    public List<Bill> findAllBillsWeb() {
        return this.billsRepository.findAll()
                .stream().sorted(Comparator.comparingLong(Bill::getId))
                .toList();
    }

    @Override
    public String calculateAllBillsForYearsRest(String year) {
        return "Sum for "
                + year +
                " year is "
                + this.billsRepository.findAll()
                .stream().filter(bill -> bill.getDate().endsWith(year))
                .mapToDouble(Bill::getSum).sum() + " lv.";
    }

}
