package com.bit.usermanagementservice.service.serviceImpl;

import com.bit.shared.config.PasswordEncoderConfig;
import com.bit.shared.entity.Role;
import com.bit.shared.entity.User;
import com.bit.shared.repository.RoleRepository;
import com.bit.shared.repository.UserRepository;
import com.bit.usermanagementservice.dto.UserRequest;
import com.bit.usermanagementservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final RoleRepository roleRepository;

    @Override
    public void addUser(UserRequest userRequest){
        boolean isAdminRoleExist = roleRepository.findByName("ADMIN").isPresent();
        boolean isInitialAdminExist = userRepository.findByEmail("admin@gmail.com").isPresent();

        Set<Role> roles = userRequest.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        User newUser = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(passwordEncoderConfig.passwordEncoder().encode(userRequest.getPassword()))
                .roles(roles)
                .build();

        if (isAdminRoleExist && roles.contains(roleRepository.findByName("ADMIN").get()) && isInitialAdminExist){
            userRepository.delete(userRepository.findByEmail("admin@gmail.com").get());
        }

        userRepository.save(newUser);
    }

    @Override
    public void updateUser(Long user_id, UserRequest userRequest) {
        User existingUser = userRepository.findById(user_id).orElse(null);

        if (existingUser != null){
            Set<Role> roles = userRequest.getRoles().stream().map(roleName ->
                            roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());

           existingUser.setName(userRequest.getName());
           existingUser.setEmail(userRequest.getEmail());
           existingUser.setPassword(passwordEncoderConfig.passwordEncoder().encode(userRequest.getPassword()));
           existingUser.setRoles(roles);

            userRepository.save(existingUser);
        }
    }

    @Override
    public void deleteUser(Long user_id) throws Exception{
        User existingUser = userRepository.findById(user_id).orElseThrow(() -> new Exception("User not found"));
        existingUser.setDeleted(true);
        userRepository.save(existingUser);
    }

}
