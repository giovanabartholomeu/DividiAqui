package com.giovana.DividiAqui.application;

import com.giovana.DividiAqui.controller.mapper.UserConverter;
import com.giovana.DividiAqui.model.User;
import com.giovana.DividiAqui.model.UserRequestDTO;
import com.giovana.DividiAqui.model.UserResponseDTO;
import com.giovana.DividiAqui.service.UserDomainServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserApplicationService {

    private final UserDomainServiceImpl userDomainService;

    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        validateUserData(requestDTO);

        if (userDomainService.getUserByEmail(requestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com esse email");
        }

        User user = UserConverter.toUser(requestDTO);

        User createdUser = userDomainService.createUser(user);

        return UserConverter.toUserResponseDTO(createdUser);
    }

    public UserResponseDTO getUserById(UUID userId) {
        User user = userDomainService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        return UserConverter.toUserResponseDTO(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userDomainService.getAllUsers();

        return users.stream()
                .map(UserConverter::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO updateUser(UUID userId, UserRequestDTO requestDTO) {
        validateUserData(requestDTO);

        User user = userDomainService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        UserConverter.updateUserFromDTO(user, requestDTO);

        User updatedUser = userDomainService.updateUser(user);

        return UserConverter.toUserResponseDTO(updatedUser);
    }

    public void deleteUserById(UUID userId) {
        User user = userDomainService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        userDomainService.deleteUserById(userId);
    }

    private void validateUserData(UserRequestDTO requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário é obrigatório");
        }

        if (requestDTO.getEmail() == null || requestDTO.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email do usuário é obrigatório");
        }

        if (!requestDTO.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("O email fornecido não é válido");
        }
    }
}

