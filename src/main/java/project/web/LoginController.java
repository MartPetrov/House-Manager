package project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.service.exception.ObjectNotFoundException;

@Controller
@RequestMapping("/users")
public class LoginController {

  @GetMapping("/login")
  public String login() {
//    throw new ObjectNotFoundException("User not found", 12);
    return "auth-login";
  }


}
