package project.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.model.dto.BuildingDTO;
import project.repositories.BillsRepository;

@Controller
@RequestMapping("/bill")
public class BillController {


    public BillController() {
    }


    @PostMapping("/add")
    public String register(BuildingDTO buildingDTO) {

//        buildingService.addBuilding(buildingDTO);
        return "redirect:/";
    }
}
