package project.entity;


import jakarta.persistence.*;
import project.enums.UserRoleEnum;


import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class UserRoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column()
  @Enumerated(EnumType.STRING)
  private UserRoleEnum role;

  public Long getId() {
    return id;
  }

  public UserRoleEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public UserRoleEnum getRole() {
    return role;
  }

  public UserRoleEntity setRole(UserRoleEnum role) {
    this.role = role;
    return this;
  }
}
