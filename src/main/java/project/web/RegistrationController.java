package project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.model.dto.UserRegistrationDTO;
import project.service.UserService;
import project.web.aop.WarnIfExecutionExceeds;

@Controller
@RequestMapping("/users")
public class RegistrationController {

  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @ModelAttribute("registerDTO")
  public UserRegistrationDTO registerDTO() {
    return new UserRegistrationDTO();
  }

  @GetMapping("/register")
  @WarnIfExecutionExceeds(
          limitMillis = 1000
  )
  public String register() {
    return "auth-register";
  }

  @PostMapping("/register")
  @WarnIfExecutionExceeds(
          limitMillis = 800
  )
  public String register(UserRegistrationDTO registerDTO) {

    userService.registerUser(registerDTO);

    return "redirect:/";
  }
}
