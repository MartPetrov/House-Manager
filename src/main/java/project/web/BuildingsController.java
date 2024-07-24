package project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.model.dto.BuildingDTO;
import project.model.entity.BillEntity;
import project.model.entity.BuildingEntity;
import project.model.entity.UserEntity;
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

    @GetMapping("/findBuildingId/{id}")
    public String findBuildingById(@PathVariable("id") Long id, Model model) {
        BuildingEntity currentBuilding = this.buildingService.findBuildingById(id);
        List<UserEntity> users = currentBuilding.getUsers();
        List<BillEntity> bills = currentBuilding.getBills();
        List<UserEntity> moderators = currentBuilding.getModerators();
        model.addAttribute("users", users);
        model.addAttribute("bills", bills);
        model.addAttribute("moderators", moderators);
        model.addAttribute("currentBuilding", currentBuilding);
        return "current-building";
    }

}
