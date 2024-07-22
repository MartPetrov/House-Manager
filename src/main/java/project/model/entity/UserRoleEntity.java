package project.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.model.enums.UserRoleEnum;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class UserRoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column()
  @Enumerated(EnumType.STRING)
  private UserRoleEnum role;

  public UserRoleEntity(UserRoleEnum role) {
    this.role = role;
  }
}
