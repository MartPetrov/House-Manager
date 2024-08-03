package project.model.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class HouseManagerUserDetails extends User {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final String phoneNumber;

  public HouseManagerUserDetails(
          String username,
          String password,
          Collection<? extends GrantedAuthority> authorities,
          String firstName,
          String lastName,
          String email, String phoneNumber
  ) {
    super(username, password, authorities);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

    public String getFullName() {
    StringBuilder fullName = new StringBuilder();
    if (firstName != null) {
      fullName.append(firstName);
    }
    if (lastName != null) {
      if (!fullName.isEmpty()) {
        fullName.append(" ");
      }
      fullName.append(lastName);
    }

    return fullName.toString();
  }
}
