package com.bit.usermanagementservice.controller;

import com.bit.usermanagementservice.dto.UserRequest;
import com.bit.usermanagementservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/admin")
@AllArgsConstructor
public class AdminController {
  private UserService userService;

  @PostMapping("/add_user")
  @ResponseStatus(HttpStatus.CREATED)
  public void addUser(@RequestBody UserRequest userRequest) {
    userService.addUser(userRequest);
  }

  @PutMapping("/update_user/{user_id}")
  public void updateUser(@PathVariable Long user_id,
                         @RequestBody UserRequest userRequest) {
    userService.updateUser(user_id, userRequest);
  }

  @DeleteMapping("/delete_user/{user_id}")
  public void deleteUser(@PathVariable Long user_id) throws Exception {
    userService.deleteUser(user_id);
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello";
  }
}
