package com.bit.usermanagementservice.service;


import com.bit.usermanagementservice.dto.UserRequest;

public interface UserService {
    void addUser(UserRequest userRequest);
    void updateUser(Long user_id, UserRequest userRequest);
    void deleteUser(Long user_id) throws Exception;

}
