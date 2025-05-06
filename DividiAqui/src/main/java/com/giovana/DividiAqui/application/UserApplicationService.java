package com.giovana.DividiAqui.application;

import com.giovana.DividiAqui.controller.mapper.UserMapper;
import com.giovana.DividiAqui.model.User;
import com.giovana.DividiAqui.model.UserRequestDTO;
import com.giovana.DividiAqui.model.UserResponseDTO;
import com.giovana.DividiAqui.service.UserDomainServiceImpl;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserApplicationService {

    private final UserDomainServiceImpl userDomainService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    /**
     * Cria um novo usuário com base nas informações fornecidas.
     *
     * @param requestDTO DTO contendo as informações do usuário a ser criado.
     * @return O DTO do usuário criado.
     * @throws IllegalArgumentException Caso o nome, email ou outros dados de entrada sejam inválidos.
     */
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        // Validação dos dados de entrada (camada de aplicação)
        validateUserData(requestDTO);

        // Verificação se o email já está em uso
        if (userDomainService.getUserByEmail(requestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com esse email");
        }

        // Mapeia o DTO para a entidade
        User user = userMapper.toUser(requestDTO);

        // Chama a camada de domínio para criar o usuário
        User createdUser = userDomainService.createUser(user);

        // Mapeia a entidade de volta para DTO para resposta
        return userMapper.toUserResponseDTO(createdUser);
    }

    /**
     * Recupera um usuário pelo seu ID.
     *
     * @param userId O ID do usuário a ser recuperado.
     * @return O DTO do usuário encontrado.
     * @throws IllegalArgumentException Se o usuário com o ID fornecido não for encontrado.
     */
    public UserResponseDTO getUserById(UUID userId) {
        // Recupera o usuário da camada de domínio
        User user = userDomainService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Mapeia para o DTO de resposta
        return userMapper.toUserResponseDTO(user);
    }

    /**
     * Recupera todos os usuários.
     *
     * @return Uma lista de DTOs contendo todos os usuários.
     */
    public List<UserResponseDTO> getAllUsers() {
        // Recupera todos os usuários da camada de domínio
        List<User> users = userDomainService.getAllUsers();

        // Mapeia cada usuário para um DTO de resposta
        return users.stream()
                .map(userMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param userId O ID do usuário a ser atualizado.
     * @param requestDTO DTO contendo as novas informações para o usuário.
     * @return O DTO do usuário atualizado.
     * @throws IllegalArgumentException Se o usuário não for encontrado ou se o nome/email forem inválidos.
     */
    public UserResponseDTO updateUser(UUID userId, UserRequestDTO requestDTO) {
        // Valida os dados
        validateUserData(requestDTO);

        // Recupera o usuário existente
        User user = userDomainService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Atualiza os dados do usuário
        userMapper.updateUserFromDTO(user, requestDTO);

        // Chama a camada de domínio para salvar a atualização
        User updatedUser = userDomainService.updateUser(user);

        // Retorna o DTO do usuário atualizado
        return userMapper.toUserResponseDTO(updatedUser);
    }

    /**
     * Exclui um usuário com base no ID fornecido.
     *
     * @param userId O ID do usuário a ser excluído.
     * @throws IllegalArgumentException Se o usuário com o ID fornecido não for encontrado.
     */
    public void deleteUserById(UUID userId) {
        // Verifica se o usuário existe
        User user = userDomainService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Chama a camada de domínio para deletar o usuário
        userDomainService.deleteUserById(userId);
    }

    /**
     * Valida os dados do usuário, como nome e email.
     *
     * @param requestDTO DTO com os dados do usuário a serem validados.
     * @throws IllegalArgumentException Se algum dado do usuário for inválido.
     */
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
