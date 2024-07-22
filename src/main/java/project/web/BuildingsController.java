package project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.model.dto.BuildingDTO;
import project.service.BuildingService;

import java.util.List;

@Controller
@RequestMapping("/building")
public class BuildingsController {
    private final BuildingService buildingService;

    public BuildingsController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @ModelAttribute("registerDTO")
    public BuildingDTO addBuildingDTO() {
        return new BuildingDTO();
    }

    @GetMapping("/add")
    public String addModerator() {
        return "add-building";
    }

    @PostMapping("/add")
    public String register(BuildingDTO buildingDTO) {

        buildingService.addBuilding(buildingDTO);

        return "redirect:/";
    }

    @GetMapping("/my")
    public String allBuildings(Model model) {

        List<BuildingDTO> myBuildings = buildingService.getAllMyBuildings();
        model.addAttribute("myBuildings", myBuildings);
        return "my-building";
    }

}
