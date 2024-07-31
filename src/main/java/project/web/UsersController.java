package project.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.model.dto.BuildingDTO;
import project.model.dto.UserInBuildingDTO;
import project.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/my-profile")
    public String showProfile() {
        return "my-profile";
    }

    @GetMapping("/addUserInBuilding" )
    public String addUserInBuilding() {
        return "addUserInBuilding";
    }

    @PostMapping("/addUserInBuilding")
    public String addUser(UserInBuildingDTO userDTO, BuildingDTO buildingDTO) {
        userService.addUserInBuilding(userDTO,buildingDTO);
        return "redirect:/building/my";
    }
    @GetMapping("/removeUserFromBuilding/{id}/{building_id}" )
    public String removeUserFromBuilding(@PathVariable(name="id") Long id, @PathVariable(name="building_id") Long building_id) {
        userService.removeUser(id, building_id);
        return "redirect:/building/" + building_id;
    }
}
