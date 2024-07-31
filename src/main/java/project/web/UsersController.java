package project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
