package com.bit.shared.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "users", name = "_users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  Long id;

  @Column(name = "user_name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @Column(name = "is_deleted")
  private boolean isDeleted;
}
