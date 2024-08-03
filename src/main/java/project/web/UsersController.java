package project.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.Banner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.model.dto.BuildingDTO;
import project.model.dto.UserInBuildingDTO;
import project.model.dto.UserUpdateDto;
import project.model.user.HouseManagerUserDetails;
import project.service.UserService;
import project.service.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/my-profile")
    public String myProfile(Model model) {
        Optional<HouseManagerUserDetails> currentUser = userService.getCurrentUser();
        if (currentUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        HouseManagerUserDetails houseManagerUserDetails = currentUser.get();
        model.addAttribute("currentUser", houseManagerUserDetails);
        return "my-profile";
    }

    @PostMapping("/my-profile")
    public String updateUser(UserUpdateDto userDTO) {
        userService.updateUser(userDTO);
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        return "redirect:/users/login";
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
