package com.giovana.DividiAqui.controller.mapper;

import com.giovana.DividiAqui.model.User;
import com.giovana.DividiAqui.model.UserRequestDTO;
import com.giovana.DividiAqui.model.UserResponseDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Converte um UserRequestDTO para uma entidade User.
     *
     * @param userRequestDTO O DTO com as informações do usuário.
     * @return A entidade User.
     */
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    User toUser(UserRequestDTO userRequestDTO);

    /**
     * Converte uma entidade User para um UserResponseDTO.
     *
     * @param user A entidade User.
     * @return O DTO de resposta do usuário.
     */
    @Mapping(source = "idUser", target = "idUser", qualifiedByName = "uuidToString")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    UserResponseDTO toUserResponseDTO(User user);

    /**
     * Atualiza um usuário com as informações de um UserRequestDTO.
     *
     * @param user O usuário a ser atualizado.
     * @param userRequestDTO O DTO com as novas informações.
     */
    @Mapping(target = "idUser", ignore = true) // Ignora a atualização do ID
    void updateUserFromDTO(@MappingTarget User user, UserRequestDTO userRequestDTO);

    /**
     * Método auxiliar para converter UUID para String.
     *
     * @param uuid O UUID a ser convertido.
     * @return A string correspondente ao UUID.
     */
    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }
}
