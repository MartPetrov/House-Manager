package project.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.model.entity.UserEntity;
import project.model.entity.UserRoleEntity;
import project.model.enums.UserRoleEnum;
import project.model.user.HouseManagerUserDetails;
import project.repositories.UserRepository;

@Service
public class HouseManagerUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public HouseManagerUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {

    return userRepository
        .findByEmail(email)
        .map(HouseManagerUserDetailsService::map)
        .orElseThrow(
            () -> new UsernameNotFoundException("User with email " + email + " not found!"));
  }

  private static UserDetails map(UserEntity userEntity) {

      return new HouseManagerUserDetails(
            userEntity.getEmail(),
            userEntity.getPassword(),
            userEntity.getRoles().stream().map(UserRoleEntity::getRole).map(HouseManagerUserDetailsService::map).toList(),
            userEntity.getFirstName(),
            userEntity.getLastName()
    );
  }

  private static GrantedAuthority map(UserRoleEnum role) {
    return new SimpleGrantedAuthority(
        "ROLE_" + role
    );
  }
}
