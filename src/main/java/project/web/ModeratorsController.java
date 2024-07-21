package project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.model.dto.UserModeratorDTO;
import project.service.UserService;

@Controller
@RequestMapping("/moderators")
public class ModeratorsController {

    private final UserService userService;

    public ModeratorsController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerDTO")
    public UserModeratorDTO registerModeratorDTO() {
        return new UserModeratorDTO();
    }

    @GetMapping("/add")
    public String addModerator() {
        return "add-moderator";
    }

    @PostMapping("/add")
    public String register(UserModeratorDTO UserModDTO) {

        userService.addModerator(UserModDTO);

        return "redirect:/";
    }
}
