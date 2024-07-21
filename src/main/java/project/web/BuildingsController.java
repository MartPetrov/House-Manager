package project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.model.dto.BuildingDTO;
import project.service.BuildingService;

@Controller
@RequestMapping("/buildings")
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
        return "add-moderator";
    }

    @PostMapping("/add")
    public String register(BuildingDTO buildingDTO) {

        buildingService.addBuilding(buildingDTO);

        return "redirect:/";
    }

}
