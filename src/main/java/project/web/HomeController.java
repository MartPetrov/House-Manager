package project.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.model.user.HouseManagerUserDetails;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home(@AuthenticationPrincipal UserDetails userDetails,
      Model model) {

    if (userDetails instanceof HouseManagerUserDetails houseManagerUserDetails) {
      model.addAttribute("welcomeMessage", houseManagerUserDetails.getFullName());
    } else {
      model.addAttribute("welcomeMessage", "Anonymous");
    }

    return "index";
  }
}
