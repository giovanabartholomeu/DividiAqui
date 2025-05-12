package com.giovana.DividiAqui.controller.mapper;

import com.giovana.DividiAqui.model.User;
import com.giovana.DividiAqui.model.UserRequestDTO;
import com.giovana.DividiAqui.model.UserResponseDTO;

public class UserConverter {

    /**
     * Converte um UserRequestDTO para uma entidade User.
     *
     * @param dto O DTO com as informações do usuário.
     * @return A entidade User.
     */
    public static User toUser(UserRequestDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

    /**
     * Converte uma entidade User para um UserResponseDTO.
     *
     * @param user A entidade User.
     * @return O DTO de resposta do usuário.
     */
    public static UserResponseDTO toUserResponseDTO(User user) {
        if (user == null) return null;

        return UserResponseDTO.builder()
                .idUser(user.getIdUser() != null ? user.getIdUser().toString() : null)
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    /**
     * Atualiza uma entidade User com base nas informações de um DTO.
     *
     * @param user A entidade a ser atualizada.
     * @param dto O DTO com as novas informações.
     */
    public static void updateUserFromDTO(User user, UserRequestDTO dto) {
        if (user == null || dto == null) return;

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        // idUser não é alterado
    }
}
