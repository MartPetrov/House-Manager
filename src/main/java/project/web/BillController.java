package project.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.model.dto.BillDTO;
import project.model.dto.BuildingDTO;
import project.service.BillService;

@Controller
@RequestMapping("/bill")
public class BillController {
    private  final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }


    @GetMapping("/removeBill/{id}/{building_id}" )
    public String removeUserFromBuilding(@PathVariable(name="id") Long id, @PathVariable(name="building_id") Long building_id) {
        billService.removeBill(id, building_id);
        return "redirect:/building/" + building_id;
    }
    @PostMapping("/addBill")
    public String addBill(BillDTO billDTO, BuildingDTO buildingDTO) {
        billService.addBill(billDTO, buildingDTO);
        return "redirect:/building/my";
    }

    @GetMapping("/addBill")
    public String addBill() {
        return "addBill";
    }
}
