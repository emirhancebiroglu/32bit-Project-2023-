package com.bit.usermanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private Set<String> roles;
}
