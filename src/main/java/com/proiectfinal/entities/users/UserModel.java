package com.proiectfinal.entities.users;

import com.proiectfinal.config.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class UserModel{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @Column(unique = true)
  @NotBlank()
  private String username;

  @NotBlank(message = "Introduceti parola")
  private String password;

  @Column(unique = true)
  private String email;


//  @OneToOne
//  @JoinColumn(name = "info_id")
  @Embedded
  private Info info;

  public Info getInfo() {
    return info;
  }

  public void setInfo(Info info) {
    this.info = info;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Enumerated(EnumType.STRING)
  private Role role;

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String name) {
    this.username = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
